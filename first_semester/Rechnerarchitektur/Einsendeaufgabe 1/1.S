.arm
.global _start
.data
    new_line:
    .ascii "\n"
    len_new_line = . -new_line

    message:
    .ascii "Revert me!"
    len_message = . -message

    reverse_message:
    .ascii ""

.align
.text
_start:
    LDR r3, =message                    @ load the message in r3
    LDR r4, =reverse_message            @ load the reverse message r4 (which is currently empty)
    LDR r10, =len_message               @ load the len in r10 (which is going to be the counter)
    ADD r3, r10                         @ move to the end of the message

counter:
    SUB r10, #1                         @ reduse the counter by 1
    CMP r10, #0                         @ lookup if the counter is equal to 0
    BEQ print                           @ if the counter is equal to 0 then print ther result
    B reverse

reverse:
    LDRB r5, [r3]                       @ load the last last letter from message in r5
    STRB r5, [r4]                       @ load the letter stored in r5 to reverse message
    SUB r3, #1                          @ step to the next letter of the message from the back
    ADD r4, #1                          @ step to the next empty letter of reverse message
    B counter

print:
    MOV r7, #4                          @ print the reverse string
    MOV r0, #1
    LDR r1, =reverse_message            @ define the printed string
    LDR r2, =len_message                @ define length of the printed string
    SWI 0

    MOV r7, #4                          @ print a new line
    MOV r0, #1
    LDR r1, =new_line                   @ define the printed string
    LDR r2, =len_new_line               @ define length of the printed string
    SWI 0

    MOV r7, #1                          @ exit the code
    SWI 0
