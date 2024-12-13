<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>Resit Perbendaharaan iSPEKS</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">SPEKS</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">
    function papar(noPenyata){

        var report =null;

  
            report = 'HSL_RESIT_PERBENDAHARAAN_MLK.rdf';
//                          var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value;

        var url = "reportName="+report+"%26report_no_pp="+noPenyata;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

    <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
        }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.ResitPerbendaharaanActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>
               
                <br />
                <p>
                    <label>No Penyata :</label>
                    <stripes:text name="noPenyata"/>
                </p>
                <p>
                    <label>Tarikh Mula:</label>
                    <stripes:text name="tarikhMula" id="datepicker" onblur="javascript:checking(this.form);" size="20;" class="datepicker" />
                </p>
                <p>
                    <label>Tarikh Akhir:</label>
                    <stripes:text name="tarikhAkhir" id="datepicker" onblur="javascript:checking(this.form);" size="20;" class="datepicker" />
                </p>
                                             
                <br />
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="populateResit" value="Papar" class="btn"/>
                    <stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/> 
                    <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
                </p>
                <br />
            </fieldset>
                <fieldset class="aras1">
                <legend>Resit Perbendaharaan </legend>
                <p class=instr>
<!--                    <em><font color="black">Sila masukkan Tarikh.</font></em>-->
                </p>
               <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listResit}"  cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil">${line_rowNum} <stripes:hidden name="idKewDok">${line.perbendaharaanResitNumber}</stripes:hidden></display:column>
                            <display:column title="No Resit" property="perbendaharaanResitNumber"/>
                            <display:column title="No Penyata" property="collectorStatementNum"/>
                            <display:column title="Tarikh Resit" property="perbendaharaanResitPostingDate"/>
                            <display:column title="Amaun"  style="text-transform:uppercase;">RM <fmt:formatNumber value="${line.perbendaharaanResitAmount}" pattern="#,##0.00"/></display:column>
                            <display:column title="Cetak Resit" style="width:10%"><stripes:button name="cetak" class="btn" onclick="papar('${line.collectorStatementNum}')">Papar</stripes:button></display:column>
                        
                        </display:table>
                    </p>
            </fieldset>
                     <p>
                   
                </p>
<!--        </div><fieldset class="aras1">
            <table style="border-collapse: collapse; width: 100%;" border="1">
<tbody>
<tr>
<td style="width: 20%;">
<p><strong><span style="color: #000080;">Senarai Tugasan</span></strong></p>
<p><strong><span style="color: #000080;">Penyata Pemungut</span></strong></p>
<ul>
    <li><t></t><strong><span style="color: #000080;">Baru</span></strong></li>
</ul>
<p><strong><span style="color: #000080;">Jurnal</span></strong></p>
<ul>
<li><strong><span style="color: #000080;">Baru</span></strong></li>
</ul>
<p><strong><span style="color: #000080;">Resit Perbendaharaan</span></strong></p>
<p><strong><span style="color: #000080;">Cek Tak Laku</span></strong></p>
<p><strong><span style="color: #000080;">Batal Perlarasan Jurnal</span></strong></p>
<p><strong><span style="color: #000080;">Terimaan Dari iSpeks</span></strong></p>
<p>&nbsp;</p>
</td>
<td style="width: 80%;">&nbsp;</td>
</tr>
</tbody>
</table>
        </fieldset>
                    <div id="template">
  <ul>
    <li class="tree-node">
      <input placeholder="filename" />
      <span class="controls">
        &raquo;
        <a class="btn-link" href="#" data-func="add-sibling"
            >+sibling</a
        >
        |
        <a class="btn-link" href="#" data-func="add-child"
            >+child</a
        >
        |
        <a class="btn-link" href="#" data-func="delete"
            >delete</a
        >
      </span>
    </li>
  </ul>
</div>-->

    </stripes:form>
</div>


    