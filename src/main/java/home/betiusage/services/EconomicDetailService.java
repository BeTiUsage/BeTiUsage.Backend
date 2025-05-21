package home.betiusage.services;

import home.betiusage.repositories.EconomicDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class EconomicDetailService {
    EconomicDetailRepository economicDetailRepository;

    public EconomicDetailService(EconomicDetailRepository economicDetailRepository) {
        this.economicDetailRepository = economicDetailRepository;
    }
}
