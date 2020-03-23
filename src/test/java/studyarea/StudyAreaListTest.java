package studyarea;

import exception.IllegalStudyAreaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StudyAreaListTest {
    /**
     * An exception pertaining to duplicate flag should be raised if the location at flag[index] is not null.
     * A dummy array of flags is created, and the first flag is assessed and used it to check duplicate
     * in the same location. The expected outcome is to return an exception since the location contains null.
     */
    @Test
    void checkDuplicate_gotDuplicate_raiseException() {
        String[] flag = {"-a", "-b"};
        Assertions.assertThrows(IllegalStudyAreaException.class, () -> StudyAreaList.checkDuplicate(flag, 0));
    }

    /**
     * An exception pertaining to flag missing hyphen should be raised when flag has no hyphen.
     * A dummy array is made with a flag inside that has no hyphen character. The expected outcome is to return
     * an exception since the input flag has no hyphen.
     */
    @Test
    void checkOnlyFlag_withoutHyphenSign_raiseException() {
        String[] commands = {"s"};
        Assertions.assertThrows(IllegalStudyAreaException.class, () -> StudyAreaList.checkOnlyFlag(commands, 0));
    }

    /**
     * An exception pertaining to either wrong flag position or invalid flag is raised when user
     * enters a flag that does not exist or user enters the flag before the search terms.
     * The expected outcome is to return an exception as this test case inputs an invalid flag.
     */
    @Test
    void checkFlag_FlagDoesNotExist_raiseException() {
        String[] commands = {"MacCommons", "-z"};
        String[] flags = {};
        Assertions.assertThrows(IllegalStudyAreaException.class, () -> StudyAreaList.checkFlag(flags, commands,
                1, false));
    }
}