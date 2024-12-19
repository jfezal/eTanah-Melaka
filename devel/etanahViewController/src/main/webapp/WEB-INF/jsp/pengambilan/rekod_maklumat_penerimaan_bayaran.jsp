<%-- 
    Document   : rekod_maklumat_penerimaan_bayaran
    Created on : Jun 1, 2010, 1:08:32 PM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

    <s:form beanclass="etanah.view.stripes.pengambilan.PendudukanSementaraBorangQActionBean">
      <%--  <s:hidden name ="hakmilik.idHakmilik"/>--%>
         <div class="subtitle">
        <fieldset class="aras1">
            <legend>Akaun Terima Bayaran</legend>
            <table>
            <p>
                <label  for="Urusan/Tujuan Permohonan">Urusan/Tujuan Permohonan :</label>
                 <s:textarea name="maksud" rows="3" cols="50" /><br />
            </p>

            <p>
                <label>Amaun (RM) :</label>
               <%-- <tr><td class="number" align="right"> <fmt:formatNumber value="${actionBean.jumlahCb}" pattern="0.00"/></td></tr>&nbsp;--%>
                <s:text name="a" size="50"/>
            </table>

        </fieldset>
         </div>

            <br />
            <div class="subtitle">
                <table align="center">
                <p>
                    <label>Cara Pembayaran : </label>
                    <s:radio name="option" value="ya"/> Cek <br />
                    <label>&nbsp;&nbsp;</label>
                    <s:radio name="option" value="tidak"/> Bank Draf <br />
                    <label>&nbsp;&nbsp;</label>
                    <s:radio name="option" value="tidak"/> Tunai <br />
                    <label>&nbsp;&nbsp;</label>
                    <s:radio name="option" value="tidak"/> Resit 
                </table>
                <p>
                    <label>No. :</label>
                    <s:text name="a" size="50"/>
                </p>

                 <p>
                    <label>Tarikh :</label>
                    <s:text name="tarikh" class="datepicker" id="tarikh"/>
                </p>

                 <p>
                    <label>Bank :</label>
                    <s:text name="a" size="50"/>
                </p>
            </div><br />

            <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                     <s:submit name="Jana" value="Jana Dokumen" class="btn"/>
                     <s:submit name="hantar" value="Hantar" class="btn"/>
                </td></tr>
            </table>
    </s:form>

