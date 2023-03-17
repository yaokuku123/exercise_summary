import openai

openai.api_key = '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'
res = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "你了解SPESC语言么"}
    ]
)
print(res['choices'][0]['message']['content'])