package com.graduation.styleguide.service;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UploadInfo;
import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.dto.UploadPostDto;
import com.graduation.styleguide.repository.UploadRepository;
import com.graduation.styleguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadService {

    private final UserRepository userRepository;
    private final UploadRepository uploadRepository;
    private final EntityManager em;

    @Value("${post.path}")
    private String uploadUrl;

    @Transactional
    public void save(UploadPostDto uploadPostDto, MultipartFile multipartFile,
                     PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + multipartFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadUrl + imgFileName);
        try {
            Files.write(imageFilePath, multipartFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        uploadRepository.save(UploadInfo.builder()
            .id(principalDetails.getUserInfo())
            .product_name(uploadPostDto.getProduct_name())
            .product_price(uploadPostDto.getProduct_price())
            .product_count(uploadPostDto.getProduct_count())
            .product_intro(uploadPostDto.getProduct_intro())
            .pic_name(imgFileName)
            .build());
    }

//    @Transactional
//    public PostInfoDto getPostInfoDto(long postId, long sessionId) {
//        PostInfoDto postInfoDto = new PostInfoDto();
//        postInfoDto.setId(postId);
//
//        Post post = postRepository.findById(postId).get();
//        postInfoDto.setTag(post.getTag());
//        postInfoDto.setText(post.getText());
//        postInfoDto.setPostImgUrl(post.getPostImgUrl());
//        postInfoDto.setCreatedate(post.getCreateDate());
//
//        //포스트 정보 요청시 포스트 엔티티의 likesCount, likesState, CommentList를 설정해준다.
//        postInfoDto.setLikesCount(post.getLikesList().size());
//        post.getLikesList().forEach(likes -> {
//            if(likes.getUser().getId() == sessionId) postInfoDto.setLikeState(true);
//        });
//        postInfoDto.setCommentList(post.getCommentList());
//
//        //포스트 주인의 정보를 가져온다.
//        User user = userRepository.findById(post.getUser().getId()).get();
//
//        postInfoDto.setPostUploader(user);
//        if(sessionId == post.getUser().getId()) postInfoDto.setUploader(true);
//        else postInfoDto.setUploader(false);
//
//        return postInfoDto;
//    }
//
//    @Transactional
//    public PostDto getPostDto(long postId) {
//        //예외 처리 필요 -> post의 작성자가 아닌 사람이 해당 페이지에 접근하여 수정하려고 한다면??
//        Post post = postRepository.findById(postId).get();
//
//        PostDto postDto = PostDto.builder()
//                .id(postId)
//                .tag(post.getTag())
//                .text(post.getText())
//                .postImgUrl(post.getPostImgUrl())
//                .build();
//
//        return postDto;
//    }
//
//    @Transactional
//    public void update(PostUpdateDto postUpdateDto) {
//        Post post = postRepository.findById(postUpdateDto.getId()).get();
//        post.update(postUpdateDto.getTag(), postUpdateDto.getText());
//    }
//
//    @Transactional
//    public void delete(long postId) {
//        Post post = postRepository.findById(postId).get();
//
//        //관련된 likes의 정보 먼저 삭제해 준다.
//        likesRepository.deleteLikesByPost(post);
//
//        //관련된 Comment의 정보 먼저 삭제해 준다.
//        commentRepository.deleteCommentsByPost(post);
//
//        //관련 파일 저장 위치에서 삭제해 준다.
//        File file = new File(uploadUrl + post.getPostImgUrl());
//        file.delete();
//
//        postRepository.deleteById(postId);
//    }
//
//    @Transactional
//    public Page<Post> getPost(long sessionId, Pageable pageable) {
//        Page<Post> postList = postRepository.mainStory(sessionId, pageable);
//
//        postList.forEach(post -> {
//            post.updateLikesCount(post.getLikesList().size());
//            post.getLikesList().forEach(likes -> {
//                if(likes.getUser().getId() == sessionId) post.updateLikesState(true);
//            });
//        });
//
//        return postList;
//    }
//
//    @Transactional
//    public Page<Post> getTagPost(String tag, long sessionId, Pageable pageable) {
//        Page<Post> postList = postRepository.searchResult(tag, pageable);
//
//        postList.forEach(post -> {
//            post.updateLikesCount(post.getLikesList().size());
//            post.getLikesList().forEach(likes -> {
//                if(likes.getUser().getId() == sessionId) post.updateLikesState(true);
//            });
//        });
//        return postList;
//    }
//
//    @Transactional
//    public Page<PostPreviewDto> getLikesPost(long sessionId, Pageable pageable) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT p.id, p.post_img_url, COUNT(p.id) as likesCount ");
//        sb.append("FROM likes l, post p ");
//        sb.append("WHERE l.post_id = p.id ");
//        sb.append("AND p.id IN (SELECT p.id FROM likes l, post p WHERE l.user_id = ? AND p.id = l.post_id) ");
//        sb.append("GROUP BY p.id ");
//        sb.append("ORDER BY p.id");
//
//        // 쿼리 완성
//        Query query = em.createNativeQuery(sb.toString())
//                .setParameter(1, sessionId);
//
//        //JPA 쿼리 매핑 - DTO에 매핑
//        JpaResultMapper result = new JpaResultMapper();
//        List<PostPreviewDto> postLikesList = result.list(query, PostPreviewDto.class);
//
//        int start = (int) pageable.getOffset();
//        int end = (start + pageable.getPageSize()) > postLikesList.size() ? postLikesList.size() : (start + pageable.getPageSize());
//
//        if(start > postLikesList.size()) return new PageImpl<PostPreviewDto>(postLikesList.subList(0, 0), pageable, 0);
//
//        Page<PostPreviewDto> postLikesPage = new PageImpl<>(postLikesList.subList(start, end), pageable, postLikesList.size());
//        return postLikesPage;
//    }
//
//    @Transactional
//    public List<PostPreviewDto> getPopularPost() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT p.id, p.post_img_url, COUNT(p.id) as likesCount ");
//        sb.append("FROM likes l, post p ");
//        sb.append("WHERE l.post_id = p.id ");
//        sb.append("AND p.id IN (SELECT p.id FROM likes l, post p WHERE p.id = l.post_id) ");
//        sb.append("GROUP BY p.id ");
//        sb.append("ORDER BY likesCount DESC, p.id ");
//        sb.append("LIMIT 12 ");
//
//        // 쿼리 완성
//        Query query = em.createNativeQuery(sb.toString());
//
//        //JPA 쿼리 매핑 - DTO에 매핑
//        JpaResultMapper result = new JpaResultMapper();
//        List<PostPreviewDto> postPreviewDtoList = result.list(query, PostPreviewDto.class);
//
//        return postPreviewDtoList;
//    }
}
