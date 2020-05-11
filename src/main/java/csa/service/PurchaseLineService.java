package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.PurchaseHeader;
import csa.model.PurchaseLine;
import csa.service.contract.IPurchaseLineService;

@Service
public class PurchaseLineService implements IPurchaseLineService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<PurchaseLine> listPurchaseLines()
	{
		return dbAdapter.listPurchaseLines();
	}

	@Override
	public List<PurchaseLine> listByPurchaseHeader(PurchaseHeader purchaseHeader)
	{
		return dbAdapter.listByPurchaseHeader(purchaseHeader);
	}

	@Override
	public PurchaseLine getPurchaseLine(int id)
	{
		return dbAdapter.getPurchaseLine(id);
	}

	@Override
	public PurchaseLine createPurchaseLine(PurchaseLine purchaseLine)
	{
		return dbAdapter.createPurchaseLine(purchaseLine);
	}

	@Override
	public boolean updatePurchaseLine(PurchaseLine purchaseLine)
	{
		return dbAdapter.updatePurchaseLine(purchaseLine);
	}

	@Override
	public boolean deletePurchaseLine(PurchaseLine purchaseLine)
	{
		return dbAdapter.deletePurchaseLine(purchaseLine);
	}
}
