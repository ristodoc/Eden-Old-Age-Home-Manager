package eden.oldagehome.service;

class Authenticator {

    private final UserRepository userRepository;

    public Authenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResult authenticate(String id, String password){
        User user = userRepository.findById(id);
        if(id.equals(user!=null&&user.checkPassword(password)){
            return AuthenticationResult(true,user.getRole());
        }
        return AuthenticationResult(false,user.getRole());
    }

    public static class AuthenticationResult {
        private final boolean success;

        private final role;

        public AuthenticationResult(boolean success,String role){
            this.success=success;
            this.role=role;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getRole() {
            return role;
        }

    }
}