package com.sehphirry.users.service.impl;

import com.sehphirry.users.dto.request.CreateUserDto;
import com.sehphirry.users.dto.request.LoginDto;
import com.sehphirry.users.dto.request.NotificationRequestDto;
import com.sehphirry.users.dto.request.OtpDto;
import com.sehphirry.users.dto.response.CustomDtoResponse;
import com.sehphirry.users.model.Users;
import com.sehphirry.users.model.VendorClient;
import com.sehphirry.users.model.VendorNumber;
import com.sehphirry.users.model.Verification;
import com.sehphirry.users.repository.UserRepository;
import com.sehphirry.users.repository.VendorClientsRepository;
import com.sehphirry.users.repository.VendorNumberRepository;
import com.sehphirry.users.repository.VerificationRepository;
import com.sehphirry.users.service.AuthService;
import com.sehphirry.users.utils.Roles;
import com.sehphirry.users.utils.VerificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

import static com.sehphirry.users.utils.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final GenericService genericService;
    private final UserRepository userRepository;
    private final VendorNumberRepository vendorNumberRepository;
    private final VendorClientsRepository vendorClientsRepository;
    private final VerificationRepository verificationRepository;
    private final WebClient.Builder webClient;
    private final MQProducer sendToQueue;

    @Override
    public ResponseEntity<CustomDtoResponse> createAccount(CreateUserDto createUserDto) {
        Users user = userRepository.findByEmail(createUserDto.getEmail());
        if (user == null){
            Users newUser = Users.builder()
                    .firstName(createUserDto.getFirstName())
                    .lastName(createUserDto.getLastName())
                    .companyName(createUserDto.getCompanyName())
                    .email(createUserDto.getEmail())
                    .role(createUserDto.getUserRole())
                    .country(createUserDto.getCountry())
                    .isActive(false)
                    .build();

            if (createUserDto.getUserRole() == Roles.CLIENT && !Objects.equals(createUserDto.getVendorNumber(), "")){
                VendorNumber venNumber = vendorNumberRepository.findByVendorNumber(createUserDto.getVendorNumber());
                if (venNumber == null){
                    return new ResponseEntity<>(new CustomDtoResponse(false, "Vendor does not exits"), HttpStatus.NOT_FOUND);
                }else{
                    log.info(newUser.toString());
                    userRepository.save(newUser);

                    VendorClient vendorClients = VendorClient.builder()
                            .vendor(venNumber.getUser())
                            .client(newUser)
                            .build();
                    vendorClientsRepository.save(vendorClients);
                }
            }else if (createUserDto.getUserRole() == Roles.VENDOR){
                List<VendorNumber> vendorNumbers = vendorNumberRepository.findAll();
                String vendorNum = "";
                if (vendorNumbers.size() > 0){
                    if (vendorNumbers.size() == 1)
                        vendorNum = vendorNumberPrefix + (Integer.parseInt(vendorNumbers.get(0).getVendorNumber().split("-")[1]) + 1);
                    else
                        vendorNum = vendorNumberPrefix + (Integer.parseInt(vendorNumbers.get(vendorNumbers.size() - 1).getVendorNumber().split("-")[1]) + 1);
                }else
                    vendorNum = vendorNumberPrefix + 1000;

                userRepository.saveAndFlush(newUser);

                CustomDtoResponse response = webClient.build().get()
                        .uri("http://billing-service/api/billing/initial-vendor-balance/"+ newUser.getId())
                        .retrieve()
                        .bodyToMono(CustomDtoResponse.class)
                        .block();

                log.info("Response from billing service {}",response);

                VendorNumber vendorNumber = VendorNumber.builder()
                        .vendorNumber(vendorNum)
                        .user(newUser)
                        .build();
                vendorNumberRepository.save(vendorNumber);
            }else {
                userRepository.save(newUser);
            }
            //TODO send email on notification service
            String otp = String.valueOf(genericService.generateOtp());
            Verification verification = new Verification(newUser, VerificationType.USER_REGISTRATION, otp, false);

            NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                    .to(newUser.getEmail())
                    .subject(EMAIL_ACCOUNT_VERIFICATION_SUBJECT)
                    .body("You account verification code is"  + otp)
                    .additional1("EMAIL")
                    .build();

            sendToQueue.sendMsg(notificationRequestDto);

            verificationRepository.save(verification);

            return new ResponseEntity<>(new CustomDtoResponse(true, USER_CREATED), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new CustomDtoResponse(true, USER_EXITS), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<CustomDtoResponse> login(LoginDto loginDto) {
        return null;
    }

    @Override
    public ResponseEntity<CustomDtoResponse> verifyOtp(OtpDto otp) {
        return null;
    }
}
