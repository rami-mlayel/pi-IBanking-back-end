package esprit.pi.SoftIB.entities;

public class AuthJwtResponse {

    private final String jwt;

    public AuthJwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
