package eu.tastr.e2e.application.testdata;

import eu.tastr.service.tasting.TastingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import static eu.tastr.domain.tasting.command.VoegTastingToeCommandTestBuilder.eenVoegTastingToeCommand;

public class TastingGenerator extends DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TastingGenerator.class);

    private final static String[] NAMEN = new String[]{
        "Bordeaux",
        "California",
        "Champagne"
    };

    private final TastingService tastingService;

    protected TastingGenerator(ApplicationContext context) {
        super(context);
        tastingService = context.getBean(TastingService.class);
    }

    public void generate(int amount) {
        LOGGER.info(String.format("Starting to generate testdata"));
        for (int i = 0; i < amount; i++) {
            if ((i + 1) % 25 == 0) {
                LOGGER.info(String.format("Creating tasting (%d/%d)", i + 1, amount));
            }
            generateTasting();
        }
        LOGGER.info(String.format("Finished generating testdata"));
    }

    private void generateTasting() {
        tastingService.create(eenVoegTastingToeCommand()
            .withNaam(NAMEN[randomNumberBetweenZeroAnd(NAMEN.length)])
            .build());
    }

    public static TastingGenerator tastingGenerator(ApplicationContext context) {
        return new TastingGenerator(context);
    }
}
