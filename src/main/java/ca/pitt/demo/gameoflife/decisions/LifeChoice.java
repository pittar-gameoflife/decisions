package ca.pitt.demo.gameoflife.decisions;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ca.pitt.demo.gameoflife.model.Lifeform;

@ApplicationScoped
public class LifeChoice {

	@Incoming("life-questions")
	@Outgoing("life-answers")
	public Lifeform answerQuestion(Lifeform aLifeForm) {
		boolean isAlive = aLifeForm.isAlive();
		int numberOfNeighbours = aLifeForm.getNeighbours();
		// The world is cruel... assume this one dies.
		aLifeForm.setAlive(false);

		// Regardless of current state, this cell will live if it has exactly 3 neighbours.
		if (numberOfNeighbours == 3) {
			aLifeForm.setAlive(true);
		}
		// It will also live if it's already alive and has 2 nice neighbours.
		else if (isAlive && (numberOfNeighbours == 2)) {
			aLifeForm.setAlive(true);
		}

		return aLifeForm;
	}

}
