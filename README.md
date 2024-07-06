# Q&A Model

This repository contains a question-answering model and tokenizer.

## Files

- `model/` - Contains the saved model.
- `tokenizer/` - Contains the saved tokenizer.

## Usage

```python
from transformers import RobertaForQuestionAnswering, RobertaTokenizerFast
import torch

# Load model and tokenizer
model = RobertaForQuestionAnswering.from_pretrained('model')
tokenizer = RobertaTokenizerFast.from_pretrained('tokenizer')

# Check if a GPU is available and if so, use it
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
model.to(device)

def answer_question(question, context):
    inputs = tokenizer.encode_plus(question, context, return_tensors="pt").to(device)
    input_ids = inputs["input_ids"].tolist()[0]
    text_tokens = tokenizer.convert_ids_to_tokens(input_ids)
    model.eval()
    with torch.no_grad():
        outputs = model(**inputs)
    answer_start_scores, answer_end_scores = outputs.start_logits, outputs.end_logits
    answer_start = torch.argmax(answer_start_scores)
    answer_end = torch.argmax(answer_end_scores) + 1
    answer = tokenizer.convert_tokens_to_string(tokenizer.convert_ids_to_tokens(input_ids[answer_start:answer_end]))
    return answer

# Example usage
context = "Your context here."
question = "Your question here."
print(answer_question(question, context))
