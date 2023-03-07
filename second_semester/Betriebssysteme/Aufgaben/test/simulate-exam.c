#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include "myqueue.h"

//my queue implementation from exercise 6
pthread_mutex_t mutexQueue = PTHREAD_MUTEX_INITIALIZER;
myqueue studentIDQueue;

struct generalInfo {
    int numberOfStudents;
    int numberOfGroups;
    int passed;
    int failed;
};

struct studentInfo {
    int studentID;
    int groupID;
    struct generalInfo *generalInfo;
};

struct instructorInfo {
    int instructorID;
    struct generalInfo *generalInfo;
};

void *student_func(void *args) {
    struct studentInfo *exam = (struct studentInfo *) args;
    int randomTime = rand() % 1000 + 100;
    int randomSolution = rand() % (exam->generalInfo->numberOfStudents * exam->generalInfo->numberOfGroups * 3) + 1;
    pthread_mutex_lock(&mutexQueue);
    myqueue_push(&studentIDQueue, exam->studentID);
    //myqueue_push(&solutionsQueue, randomSolution);
    printf("Student %d in group %d has finished exam after %d ms with solution %d\n", exam->studentID, exam->groupID,
           randomTime, randomSolution);
    pthread_mutex_unlock(&mutexQueue);
    free(exam);
    return NULL;
}

void *instructor_func(void *args) {
    struct instructorInfo *exam = (struct instructorInfo *) args;
    while (1) {
        int randomTime = rand() % 300 + 100;
        int randomGrade = rand() % 100 + 1;

        pthread_mutex_lock(&mutexQueue);
        if (myqueue_is_empty(&studentIDQueue)) {
            pthread_mutex_unlock(&mutexQueue);
            free(exam);
            return NULL;
        } else {
            pthread_mutex_unlock(&mutexQueue);
            pthread_mutex_lock(&mutexQueue);
            int studentID = myqueue_pop(&studentIDQueue);
            pthread_mutex_unlock(&mutexQueue);
            if (randomGrade >= 50) {
                printf("Instructor %d graded student %d after %d ms. They achieved a score of %d%%: PASS\n",
                       exam->instructorID, studentID, randomTime, randomGrade);
                exam->generalInfo->passed++;
            } else {
                printf("Instructor %d graded student %d after %d ms. They achieved a score of %d%%: FAIL\n",
                       exam->instructorID, studentID, randomTime, randomGrade);
                exam->generalInfo->failed++;
            }
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: <number of groups> <students per group\n");
        return EXIT_FAILURE;
    }
    int numGroups = (int) strtol(argv[1], NULL, 10);
    int studentsPerGroup = (int) strtol(argv[2], NULL, 10);
    if (numGroups <= 0) {
        fprintf(stderr, "Number of groups must be greater than 0\n");
        return EXIT_FAILURE;
    }
    if (studentsPerGroup <= 0) {
        fprintf(stderr, "Students per group must be greater than 0\n");
        return EXIT_FAILURE;
    }

    int numberOfInstructors = numGroups;
    pthread_t instructors[numberOfInstructors];
    pthread_t students[studentsPerGroup * numGroups];
    myqueue_init(&studentIDQueue);
    //myqueue_init(&solutionsQueue);

    struct generalInfo *generalInfo = malloc(sizeof(struct generalInfo));
    generalInfo->numberOfStudents = studentsPerGroup;
    generalInfo->numberOfGroups = numGroups;
    generalInfo->passed = 0;
    generalInfo->failed = 0;
    srand(time(NULL));

    for (int i = 0; i < studentsPerGroup * numGroups; i++) {
        struct studentInfo *solutionContent = malloc(sizeof(struct studentInfo));
        solutionContent->studentID = i;
        solutionContent->groupID = i % numGroups;
        solutionContent->generalInfo = generalInfo;
        if (pthread_create(&students[i], NULL, student_func, solutionContent) != 0) {
            fprintf(stderr, "Error creating student thread\n");
            free(solutionContent);
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < numberOfInstructors; i++) {
        struct instructorInfo *instructorContent = malloc(sizeof(struct instructorInfo));
        instructorContent->instructorID = i;
        instructorContent->generalInfo = generalInfo;
        if (pthread_create(&instructors[i], NULL, instructor_func, instructorContent) != 0) {
            fprintf(stderr, "Error creating instructor thread\n");
            free(instructorContent);
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < studentsPerGroup; i++) {
        if (pthread_join(students[i], NULL) != 0) {
            fprintf(stderr, "Error joining student thread\n");
            return EXIT_FAILURE;
        }
    }

    for (int i = 0; i < numberOfInstructors; i++) {
        if (pthread_join(instructors[i], NULL) != 0) {
            fprintf(stderr, "Error joining instructor thread\n");
            return EXIT_FAILURE;
        }
    }

    printf("%d students passed and %d failed\n", generalInfo->passed, generalInfo->failed);
    free(generalInfo);
    pthread_mutex_destroy(&mutexQueue);
    return EXIT_SUCCESS;
}
