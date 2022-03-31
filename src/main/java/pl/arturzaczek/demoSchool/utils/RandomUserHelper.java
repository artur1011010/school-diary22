package pl.arturzaczek.demoSchool.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.service.RoleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class RandomUserHelper {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    List<String> studentNamesM = new ArrayList<>(List.of("Artur", "Michał", "Marcin", "Mateusz", "Krzysztof", "Piotr", "Paweł", "Adrian", "Kamil", "Sebastian", "Przemysław", "Dawid", "Karol", "Tomasz", "Wojciech"));
    List<String> studentNamesF = new ArrayList<>(List.of("Anna", "Dorota", "Katarzyna", "Justyna", "Beata", "Julia", "Marta", "Natalia", "Kamila", "Małgorzata", "Karolina", "Klaudia", "Magdalena", "Ewa"));
    List<String> studentLastNamesM = new ArrayList<>(List.of("Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Grabowski",
            "Lewandowski", "Zieliński", "Szymański", "Woźniak", "Kozłowski", "Mazur", "Krawczyk", "Kaczmarek", "Pawłowski", "Zając", "Baran", "Borkowski"));
    List<String> studentLastNamesF = new ArrayList<>(List.of("Nowak", "Kowalska", "Wiśniewska", "Kowalczyk", "Kamińska", "Lewandowska", "Zielińska", "Sikora", "Pawlak", "Adamczyk", "Dudek", "Jaworska",
            "Szymańska", "Woźniak", "Dąbrowska", "Krawczyk", "Olszewska"));
    List<String> emails = new ArrayList<>(List.of("@tlen.pl", "@gmail.com", "@onet.pl", "@outlook.com", "@AOL.com", "@gmail.com"));


    public List<User> createRandomUserM() {
        log.info("createRandomUserM()");
        Collections.shuffle(studentNamesM);
        Collections.shuffle(studentLastNamesM);
        Collections.shuffle(emails);
        final List<User> users = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final User user = new User();
            user.setFirstName(studentNamesM.get(i));
            user.setLastName(studentLastNamesM.get(i));
            String simplifiedEmail = simplifyLatinChars(studentNamesM.get(i) + "." + studentLastNamesM.get(i)) + random.nextInt(1000) + emails.get(i / 2);
            user.setEmail(simplifiedEmail);
            user.setBirthDate(between());
            user.setPasswordHash(passwordEncoder.encode(simplifiedEmail));
            roleService.getORCreateDefaultRole(user, RoleEnum.ROLE_USER);
            users.add(user);
        }
        return users;
    }

    public List<User> createRandomUserF() {
        log.info("createRandomUserF()");
        Collections.shuffle(studentNamesF);
        Collections.shuffle(studentLastNamesF);
        Collections.shuffle(emails);
        final List<User> users = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final User user = new User();
            user.setFirstName(studentNamesF.get(i));
            user.setLastName(studentLastNamesF.get(i));
            String simplifiedEmail = simplifyLatinChars(studentNamesF.get(i) + "." + studentLastNamesF.get(i)) + random.nextInt(1000) + emails.get(i / 2);
            user.setEmail(simplifiedEmail);
            user.setBirthDate(between());
            user.setPasswordHash(passwordEncoder.encode(simplifiedEmail));
            roleService.getORCreateDefaultRole(user, RoleEnum.ROLE_USER);
            users.add(user);
        }
        return users;
    }

    private LocalDate between() {
        final LocalDate startInclusive = LocalDate.of(1970, 1, 1);
        final LocalDate endExclusive = LocalDate.of(2005, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private String simplifyLatinChars(final String inputString) {
        final char[] input = inputString.toCharArray();
        final StringBuilder output = new StringBuilder();
        int inputLength = input.length;
        for (int i = 0; i < inputLength; i++) {
            switch (input[i]) {
                case 'ą':
                    output.append('a');
                    break;
                case 'ć':
                    output.append('c');
                    break;
                case 'ę':
                    output.append('e');
                    break;
                case 'ł':
                    output.append('l');
                    break;
                case 'ń':
                    output.append('n');
                    break;
                case 'ó':
                    output.append('o');
                    break;
                case 'ś':
                    output.append('s');
                    break;
                case 'ź':
                    output.append('z');
                    break;
                case 'ż':
                    output.append('z');
                    break;
                case 'Ą':
                    output.append('A');
                    break;
                case 'Ć':
                    output.append('C');
                    break;
                case 'Ę':
                    output.append('E');
                    break;
                case 'Ł':
                    output.append('L');
                    break;
                case 'Ń':
                    output.append('N');
                    break;
                case 'Ó':
                    output.append('O');
                    break;
                case 'Ś':
                    output.append('S');
                    break;
                case 'Ź':
                    output.append('Z');
                    break;
                case 'Ż':
                    output.append('Z');
                    break;
                default:
                    output.append(input[i]);
                    break;
            }
        }
        return output.toString();
    }
}
