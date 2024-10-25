package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.config.AuthenticationRequest;
import tigo.aplanchados.config.AuthenticationResponse;
import tigo.aplanchados.config.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
