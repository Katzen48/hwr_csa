package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.PurchaseHeader;
import csa.model.PurchaseLine;

@Contract
public interface IPurchaseLineService
{
	List<PurchaseLine> listPurchaseLines();
	List<PurchaseLine> listByPurchaseHeader(PurchaseHeader purchaseHeader);
	PurchaseLine getPurchaseLine(int id);
	PurchaseLine createPurchaseLine(PurchaseLine purchaseLine);
	boolean updatePurchaseLine(PurchaseLine purchaseLine);
	boolean deletePurchaseLine(PurchaseLine purchaseLine);
}
