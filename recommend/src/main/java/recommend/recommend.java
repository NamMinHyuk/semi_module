package recommend;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class recommend {
	
	public static void main(String[] args) throws IOException, TasteException {
		DataModel dm = new FileDataModel(new File("./src/result.csv"));		// 파일 불러와서 DataModel로 사용해서 객체 생성
		
		UserSimilarity sim = new PearsonCorrelationSimilarity(dm);			// 유사도 모델 생성
		
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, sim, dm); // 모든 회원들과 주어진 회원과 특정 임계값을 충족하거나 초과하는 neighborhood 기준
		
		UserBasedRecommender recommender = new GenericUserBasedRecommender(dm, neighborhood, sim); // 사용자 기반 추천기 생성
		
		int x=1;
		for(LongPrimitiveIterator users = dm.getUserIDs(); users.hasNext();) { // 데이터 모델 내의 유저들의 iterator를 단계별로 이동하면서 추천 상품을 제공해줌
			long userId = users.nextLong();	// 현재 유저의 id
			List<RecommendedItem> recommendations = recommender.recommend(userId, 10); // 현재 유저id에 상품 추천 10개
			for(RecommendedItem recommenation:recommendations) {
				System.out.println("회원번호 : " + userId+" , 추천 상품 번호 : "+recommenation.getItemID()+" , 유사도(0~5) : " +recommenation.getValue());
			}

			if(++x > 5) break; // 회원 id 5명까지 출력
		}
	}
}
