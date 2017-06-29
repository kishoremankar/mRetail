package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.model.CurrentPrice;

public interface ProductPriceRepository extends MongoRepository<CurrentPrice, String>{
	public CurrentPrice findCurrentPriceByProductId(Long productId);
}
