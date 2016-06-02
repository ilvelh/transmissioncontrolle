package ca.teyssedre.android.transmissioncontrol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;
import ca.teyssedre.android.transmissioncontrol.model.request.ListArgRequest;
import ca.teyssedre.android.transmissioncontrol.model.request.TransmissionRequest;
import ca.teyssedre.android.transmissioncontrol.model.response.ListArgsResponse;
import ca.teyssedre.restclient.HttpClient;
import ca.teyssedre.restclient.HttpRequest;
import ca.teyssedre.restclient.HttpResponse;
import ca.teyssedre.restclient.NoSSLValidation;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

}