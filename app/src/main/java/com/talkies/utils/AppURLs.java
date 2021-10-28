package com.talkies.utils;

public class AppURLs {

        //public static String BASE_URL="https://tv5e-api.talkiesapp.com/api/v3/";//dev
        public static String BASE_URL="https://talkiesapp.com/api/v3/"; // prod

        public static  final String login = BASE_URL+"auth/login/";
        public static  final String getToken = "https://tv5e-api.talkiesapp.com/api/auth/social_auth/";
        public static  final String otpVerifyLogin = BASE_URL+"auth/validate_token/";
        public static  final String signUp = BASE_URL+"auth/signup/";
        public static  final String getUserDetails = BASE_URL+"auth/user/";
        public static  final String completeUserProfile = BASE_URL+"auth/user/";
        public static  final String topImageBanner = BASE_URL+"media/masthead/?video_section_type=";
        public static  final String continueWatchingList = BASE_URL+"media/currently_watching/?video_section_type=";

        public static  final String categoryList = BASE_URL+"browse/?video_section_type=";
        public static  final String notificationAlert = BASE_URL+"notification/alert/";
        public static  final String searchForQuery = BASE_URL+"media/masthead/?q=";

        public static  final String searchDetails = BASE_URL+"media/";
        public static  final String getTabsCategory = BASE_URL+"media/video_section_type/";

        public static  final String faqList = BASE_URL+"faqs/";

        public static  final String connectTV = BASE_URL+"auth/tv/user_pair_device/";
        public static  final String unpairTV = BASE_URL+"auth/user_pair_device/";

        public static  final String submitQuery = BASE_URL+"contact/";
        public static  final String issueTypeList = BASE_URL+"/contact/issue/";

        public static  final String saveNameProfile = BASE_URL+"auth/user/";
        public static  final String savePhoneProfile = BASE_URL+"auth/user/";
        public static  final String saveEmailProfile = BASE_URL+"auth/user/";

        public static  final String rentalDetails = BASE_URL+"media/rental_info/history/rental_type=";
        public static  final String expiredDetails = BASE_URL+"media/rental_info/history/?is_expired=";

        public static  final String subscriptionList = BASE_URL+"media/subscription/";

        public static  final String announcements = BASE_URL+"notification/announcement_section/";
        //public static  final String announcementsReleaseDatesList = BASE_URL+"notification/announcement_section/";
        public static  final String releaseDatesList = BASE_URL+"media/release_dates/";

        public static  final String notificationList = BASE_URL+"notification/";
        public static  final String clearAllNotificationList = BASE_URL+"notification/";

        public static  final String moreClicked = BASE_URL+"media/list/?video_section_type=";

        public static  final String logoutUser = BASE_URL+"auth/logout/";

        public static  final String rentalRazKeyInfo = BASE_URL+"media/rental_info/rent/";

    }
