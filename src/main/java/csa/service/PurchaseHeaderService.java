package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.PurchaseHeader;
import csa.service.contract.IPurchaseHeaderService;

@Service
public class PurchaseHeaderService implements IPurchaseHeaderService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<PurchaseHeader> listPurchaseHeaders()
	{
		return dbAdapter.listPurchaseHeaders();
	}

	@Override
	public PurchaseHeader getPurchaseHeader(int id)
	{
		return dbAdapter.getPurchaseHeader(id);
	}

	@Override
	public PurchaseHeader createPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.createPurchaseHeader(purchaseHeader);
	}

	@Override
	public boolean updatePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.updatePurchaseHeader(purchaseHeader);
	}

	@Override
	public boolean deletePurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.deletePurchaseHeader(purchaseHeader);
	}
}
