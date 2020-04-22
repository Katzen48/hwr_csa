package csa.service.contract;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import csa.model.SalesHeader;
import csa.model.SalesLine;

@Contract
public interface ISalesLineService
{
	List<SalesLine> listSalesLines();
	List<SalesLine> listBySalesHeader(SalesHeader salesHeader);
	SalesLine getSalesLine(int id);
	SalesLine createSalesLine(SalesLine salesLine);
	boolean updateSalesLine(SalesLine salesLine);
	boolean deleteSalesLine(SalesLine salesLine);
}