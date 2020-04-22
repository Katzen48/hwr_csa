package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.PurchaseHeader;

@Contract
public interface IPurchaseHeaderService
{
	List<PurchaseHeader> listPurchaseHeaders();
	PurchaseHeader getPurchaseHeader(int id);
	PurchaseHeader createPurchaseHeader(PurchaseHeader purchaseHeader);
	boolean updatePurchaseHeader(PurchaseHeader purchaseHeader);
	boolean deletePurchaseHeader(PurchaseHeader purchaseHeader);
}
