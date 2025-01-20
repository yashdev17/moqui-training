import org.moqui.entity.EntityValue
import org.moqui.entity.EntityList
import org.moqui.context.ExecutionContext

// Get execution context
ExecutionContext ec = context

// Define the input parameters for the service
String trainingName = ec.getInput().get('trainingName')
String trainingDate = ec.getInput().get('trainingDate')

// Validate the inputs
if (!trainingName) {
    throw new RuntimeException("Training name is mandatory.")
}
if (!trainingDate || !trainingDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
    throw new RuntimeException("Training date must follow MM/dd/yyyy format.")
}

// Create a new entity value for MoquiTraining
EntityValue trainingEntity = ec.entity.makeValue("MoquiTraining")
trainingEntity.set("trainingName", trainingName)
trainingEntity.set("trainingDate", trainingDate)

// Save the entity
trainingEntity.create()

// Return the trainingId
return trainingEntity.get("trainingId")
