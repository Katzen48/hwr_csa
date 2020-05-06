package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.SalesHeader;
import csa.service.contract.ISalesHeaderService;

@Service
public class SalesHeaderService implements ISalesHeaderService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<SalesHeader> listSalesHeaders()
	{
		return dbAdapter.listSalesHeaders();
	}

	@Override
	public SalesHeader getSalesHeader(int id)
	{
		return dbAdapter.getSalesHeader(id);
	}

	@Override
	public SalesHeader createSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.createSalesHeader(salesHeader);
	}

	@Override
	public boolean updateSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.updateSalesHeader(salesHeader);
	}

	@Override
	public boolean deleteSalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.deleteSalesHeader(salesHeader);
	}
}
