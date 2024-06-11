package com.example.mindspaceBE.services;

import com.example.mindspaceBE.models.AnxietyQuizDTO;
import com.example.mindspaceBE.models.DepressionQuizDTO;
import com.example.mindspaceBE.models.UserDTO;
import com.example.mindspaceBE.models.entities.AnxietyQuizResult;
import com.example.mindspaceBE.models.entities.DepressionQuizResult;
import com.example.mindspaceBE.models.entities.User;
import com.example.mindspaceBE.repositories.AnxietyQuizResultRepository;
import com.example.mindspaceBE.repositories.DepressionQuizResultRepository;
import com.example.mindspaceBE.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AnxietyQuizResultRepository anxietyQuizResultRepository;
    private final DepressionQuizResultRepository depressionQuizResultRepository;

    public AuthenticationService(UserRepository userRepository, AnxietyQuizResultRepository anxietyQuizResultRepository, DepressionQuizResultRepository depressionQuizResultRepository) {
        this.userRepository = userRepository;
        this.anxietyQuizResultRepository = anxietyQuizResultRepository;
        this.depressionQuizResultRepository = depressionQuizResultRepository;
    }

    public User registerNewUser(UserDTO userDTO) {
        User user = new User();

        String hashedPass;

        try {
            hashedPass = encryptPassword(userDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        user.setFullName(userDTO.getFullName());
        user.setGender(userDTO.getGender());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashedPass);

        userRepository.save(user);

        return user;
    }

    public User logInUser(UserDTO userDTO) {
        try {
            String hashedPass = encryptPassword(userDTO.getPassword());
            User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), hashedPass);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
            }

            return user;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public User getUserProfile(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public AnxietyQuizResult saveAnxietyQuizResult(AnxietyQuizDTO anxietyQuizDTO) {
        AnxietyQuizResult anxietyQuizResult = new AnxietyQuizResult();
        anxietyQuizResult.setEmail(anxietyQuizDTO.getEmail());
        anxietyQuizResult.setTotalQuestions(anxietyQuizDTO.getTotalQuestions());
        anxietyQuizResult.setCorrectAnswers(anxietyQuizDTO.getCorrectAnswers());
        return anxietyQuizResultRepository.save(anxietyQuizResult);
    }

    public DepressionQuizResult saveDepressionQuizResult(DepressionQuizDTO depressionQuizDTO) {
        DepressionQuizResult depressionQuizResult = new DepressionQuizResult();
        depressionQuizResult.setEmail(depressionQuizDTO.getEmail());
        depressionQuizResult.setTotalQuestions(depressionQuizDTO.getTotalQuestions());
        depressionQuizResult.setCorrectAnswers(depressionQuizDTO.getCorrectAnswers());
        return depressionQuizResultRepository.save(depressionQuizResult);
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytesPass = md.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte pass : bytesPass) {
            stringBuilder.append(Integer.toHexString(0xFF & pass));
        }
        return stringBuilder.toString();
    }
}


//
//    private final UserRepository userRepository;
//    private final AnxietyQuizResultRepository anxietyQuizResultRepository;
//    private final DepressionQuizResultRepository depressionQuizResultRepository;
//    public AuthenticationService(UserRepository userRepository, AnxietyQuizResultRepository anxietyQuizResultRepository, DepressionQuizResultRepository depressionQuizResultRepository) {
//        this.userRepository = userRepository;
//        this.anxietyQuizResultRepository = anxietyQuizResultRepository;
//    }
//
//    public User registerNewUser(UserDTO userDTO) {
//        User user = new User();
//
//        String hashedPass;
//
//        try {
//            hashedPass = encryptPassword(userDTO.getPassword());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//        user.setFullName(userDTO.getFullName());
//        user.setGender(userDTO.getGender());
//        user.setAge(userDTO.getAge());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(hashedPass);
//
//        userRepository.save(user);
//
//        return user;
//    }
//
//    public User logInUser(UserDTO userDTO) {
//        try {
//            String hashedPass = encryptPassword(userDTO.getPassword());
//            User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), hashedPass);
//
//            if (user == null) {
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
//            }
//
//            return user;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//    }
//
//    private String encryptPassword(String password) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(password.getBytes());
//        byte[] bytesPass = md.digest();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (byte pass : bytesPass) {
//            stringBuilder.append(Integer.toHexString(0xFF & pass));
//        }
//        return stringBuilder.toString();
//    }
//
//    public User getUserProfile(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        return optionalUser.orElse(null);
//    }
//
//    public AnxietyQuizResult saveAnxietyQuizResult(AnxietyQuizDTO anxietyQuizDTO) {
//        AnxietyQuizResult anxietyQuizResult = new AnxietyQuizResult();
//        anxietyQuizResult.setEmail(anxietyQuizDTO.getEmail());
//        anxietyQuizResult.setTotalQuestions(anxietyQuizDTO.getTotalQuestions());
//        anxietyQuizResult.setCorrectAnswers(anxietyQuizDTO.getCorrectAnswers());
//        return anxietyQuizResultRepository.save(anxietyQuizResult);
//    }
//
//    public DepressionQuizResult saveDepressionQuizResult(DepressionQuizDTO depressionQuizDTO) {
//        DepressionQuizResult depressionQuizResult = new DepressionQuizResult();
//        depressionQuizResult.setEmail(depressionQuizDTO.getEmail());
//        depressionQuizResult.setTotalQuestions(depressionQuizDTO.getTotalQuestions());
//        depressionQuizResult.setCorrectAnswers(depressionQuizDTO.getCorrectAnswers());
//        return depressionQuizResultRepository.save(depressionQuizResult);
//    }
//}