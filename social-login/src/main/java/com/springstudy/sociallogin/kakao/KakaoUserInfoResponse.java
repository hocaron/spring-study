package com.springstudy.sociallogin.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 카카오 사용자 정보 조회 DTO.
 */
@Getter
public class KakaoUserInfoResponse {

    /**
     * 카카오 회원번호.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * 사용자 프로퍼티.
     */
    @JsonProperty("properties")
    private Properties properties;

    /**
     * 카카오계정 정보.
     */
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    /**
     * 카카오싱크 간편가입을 통해 로그인한 일시.
     */
    @JsonProperty("synched_at")
    private String synchedAt;

    /**
     * 서비스에 연결 완료된 일시.
     */
    @JsonProperty("connected_at")
    private String connectedAt;

    @Getter
    public static class KakaoAccount {

        @JsonProperty("birthday_needs_agreement")
        private Boolean birthdayNeedsAgreement;

        @JsonProperty("birthyear_needs_agreement")
        private Boolean birthyearNeedsAgreement;

        @JsonProperty("birthyear")
        private String birthyear;

        @JsonProperty("birthday")
        private String birthday;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("email_needs_agreement")
        private Boolean emailNeedsAgreement;

        @JsonProperty("gender_needs_agreement")
        private Boolean genderNeedsAgreement;

        @JsonProperty("has_birthday")
        private Boolean hasBirthday;

        @JsonProperty("has_birthyear")
        private Boolean hasBirthyear;

        @JsonProperty("has_email")
        private Boolean hasEmail;

        @JsonProperty("has_gender")
        private Boolean hasGender;

        @JsonProperty("has_phone_number")
        private Boolean hasPhoneNumber;

        @JsonProperty("is_email_valid")
        private Boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private Boolean isEmailVerified;

        @JsonProperty("phone_number_needs_agreement")
        private Boolean phoneNumberNeedsAgreement;

        @JsonProperty("profile")
        private Profile profile;

        @JsonProperty("profile_needs_agreement")
        private Boolean profileNeedsAgreement;

        // Getter methods for all fields
        // ...
    }

    @Getter
    public static class Properties {

        @JsonProperty("nickname")
        private String nickname;
    }

    @Getter
    public static class Profile {

        @JsonProperty("is_default_image")
        private Boolean isDefaultImage;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image_url")
        private String profileImageUrl;

        @JsonProperty("thumbnail_image_url")
        private String thumbnailImageUrl;
    }
}
