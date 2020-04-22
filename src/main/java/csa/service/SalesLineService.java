package csa.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import csa.database.DatabaseAdapter;
import csa.model.SalesHeader;
import csa.model.SalesLine;
import csa.service.contract.ISalesLineService;

@Service
public class SalesLineService implements ISalesLineService
{
	@Inject
	private DatabaseAdapter dbAdapter;
	
	@Override
	public List<SalesLine> listSalesLines()
	{
		return dbAdapter.listSalesLines();
	}

	@Override
	public List<SalesLine> listBySalesHeader(SalesHeader salesHeader)
	{
		return dbAdapter.listBySalesHeader(salesHeader);
	}

	@Override
	public SalesLine getSalesLine(int id)
	{
		return dbAdapter.getSalesLine(id);
	}

	@Override
	public SalesLine createSalesLine(SalesLine salesLine)
	{
		return dbAdapter.createSalesLine(salesLine);
	}

	@Override
	public boolean updateSalesLine(SalesLine salesLine)
	{
		return dbAdapter.updateSalesLine(salesLine);
	}

	@Override
	public boolean deleteSalesLine(SalesLine salesLine)
	{
		return dbAdapter.deleteSalesLine(salesLine);
	}
}
