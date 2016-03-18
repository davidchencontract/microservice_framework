package uk.gov.justice.raml.jms.it.handler;

import javax.ejb.Singleton;

import uk.gov.justice.services.core.annotation.Component;
import uk.gov.justice.services.core.annotation.Handles;
import uk.gov.justice.services.core.annotation.ServiceComponent;
import uk.gov.justice.services.messaging.Envelope;

@ServiceComponent(Component.COMMAND_HANDLER)
@Singleton
public class CommandHandlerHandler extends BasicHandler {
    @Handles("structure.commands.cmdaa")
    public void handleCommandA(final Envelope envelope) {
        receivedEnvelope = envelope;
    }

    @Handles("structure.commands.cmdcc")
    public void handleCommandC(final Envelope envelope) {
        receivedEnvelope = envelope;
    }
}