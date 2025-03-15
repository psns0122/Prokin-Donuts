package service;

import dto.InboundDTO;
import dto.ProductDTO;

import java.util.List;

public interface InboundService {

    //[입고 검수 Service]

    /**
     * 입고(승인) 고지서를 반환하는 service
     * findByApprovedStatus 사용
     *
     * @return 입고승인 리스트
     */
    List<InboundDTO> getInboundList();

    /**
     * 입고(승인) ok 되면 입고 완료! service
     * @param inboundId 입고 아이디
     */
    void completedInbound(int inboundId);

    //[입고 요청 Service]
    /**
     * 입고 요청시 상품 Menu 정보
     */
    List<ProductDTO> getProductMenu();

    /**
     * 입고 요청 등록
     */
    void registerInbound(List<ProductDTO> inboundList);

    /**
     * 입고 상세 등록 -> 입고 요청 등록에서 사용
     */
    ProductDTO addProduct(ProductDTO productDTO);

}
