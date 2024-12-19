<%--
	Document: pertanyaan_deposit_tidak_dituntut
	Author: Mohd Hairudin Mansur
	Version: 1.0 21 December 2009
 --%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle">PERTANYAAN DEPOSIT TIDAK DITUNTUT</div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanDepositTidakDituntutActionBean">
	<div class="subtitle">
		<fieldset class="aras1">
			<legend>Carian Deposit Tidak Dituntut</legend>
			<div class="content" align="center">
				
					<table cellspacing="4">
						<tr>
							<td class="rowlabel1"><label>No. Akaun Deposit :</label></td>
							<td class="input1"><s:text name="kodAkaunDeposit" id="kodAkaunDeposit" /></td>
						</tr>
						<tr>
							<td class="rowlabel1"><label>Tarikh Dari :</label></td>
							<td class="input1"><s:text name="tarikhDari" id="datepicker" class="datepicker"/></td>
						</tr>
						<tr>
							<td class="rowlabel1"><label>Tarikh Hingga :</label></td>
							<td class="input1"><s:text name="tarikhHingga" id="datepicker2" class="datepicker"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<s:submit name="searchDepositTidakDituntut" value="Cari" class="btn"/>
	                            <s:reset  name="reset" value="Isi Semula" class="btn"/>
							</td>
						</tr>
					</table>
				
			</div>
		</fieldset>
	</div>
	<br/>
	
	<c:if test="${actionBean.showDisplayTable}">
		<div class="subtitle">
			<fieldset class="aras1">
				<legend>Senarai Deposit Tidak Dituntut</legend>
				<div class="content" align="center">
					<display:table class="tablecloth" name="${actionBean.listTransaksiDeposit}" pagesize="4" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_deposit_tidak_dituntut" id="line">
						<display:column title="Pilih">
							<div align="center"><s:checkbox name="checkBox" value="${line.idTransaksi}" /></div>
						</display:column>
						<display:column title="Bil." sortable="true">${line_rowNum}</display:column>
						<display:column title="Tarikh">
							<s:format formatPattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
						</display:column>
						<display:column title="Jenis Transaksi">
							${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
						</display:column>
						<display:column property="dokumenKewangan.idDokumenKewangan" title="No. Resit" />
						<display:column property="amaun" title="Amaun (RM)" />
					</display:table>
				</div>
				<div align="right">
					<s:button name="hantar" id="hantar" onclick="" class="btn">Hantar</s:button>
					<s:button name="cetak" id="cetak" onclick="" class="btn">Cetak</s:button>
				</div>
			</fieldset>
		</div>
	</c:if>
</s:form>