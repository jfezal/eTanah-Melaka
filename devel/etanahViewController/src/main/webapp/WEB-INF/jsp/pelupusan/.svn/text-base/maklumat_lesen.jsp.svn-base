<%--
    Document   : maklumat_lesen
    Created on : Mar 24, 2011, 6:17:38 PM
    Author     : Rohan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">

    </script>

<s:form  beanclass="etanah.view.stripes.pelupusan.MaklumatLesenActionBean">
            <s:errors/>
            <s:messages/>
      <div class="subtitle">
          <c:if test="${!flag}">
        <fieldset class="aras1">
            <legend>Carian No Lesen</legend>

            <p>
             <label for="ID Permit">ID Permohonan :</label>
             <s:text name="idPermohonan1" size="30" />
            </p>
            
             <p>
             <label for="No Permit">No Lesen :</label>
             <s:text name="noPermit1" size="30" />
            </p>

            <p align="center">
                 <s:button class="btn" value="Cari" name="cariPage"  onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
            </p>

        </fieldset>
    </c:if>
    </div>
            <c:if test="${flag}">
             <fieldset class="aras1">
                <legend>Maklumat Lesen Pendudukan Sementara</legend>

                <div class="content">
                    <table align="center" width="50%" border="0">
                    <tr>
                        <td  width="20%" style="color: #003194; font-weight: 700">  No Lesen </td>
                        <td><b> : </b></td>
                        <td>${actionBean.permit.noPermit}</td>
                    </tr>

                    <tr>
                         <td style="color: #003194; font-weight: 700"> Kegunaan : </td>
                         <td><b> : </b></td>
                         <td>${actionBean.permitItem.kodItemPermit.nama}</td>
                    </tr>

                    <tr>
                        <td style="color: #003194; font-weight: 700">  Tarik Keluar : </td>
                        <td><b> : </b></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermit}"/></td>
                    </tr>

                    <tr>
                        <td style="color: #003194; font-weight: 700">  Tarik Mula : </td>
                        <td><b> : </b></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermitMula}"/></td>
                    </tr>

                    <tr>
                         <td style="color: #003194; font-weight: 700"> Tarik Tamat : </td>
                         <td><b> : </b></td>
                         <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permitSahLaku.tarikhPermitTamat}"/></td>
                    </tr>

                
                    </table>
                </div>
             </fieldset>
                
             <fieldset class="aras1">
                <legend>Maklumat Pemegang Lesen Pendudukan Sementara</legend>

                <div class="content">
                    <table align="center" width="50%" border="0">
                    <tr>
                         <td  width="20%" style="color: #003194; font-weight: 700">Hali ke Brp</td>
                         <td><b> : </b></td>
                           <td>${actionBean.noOfRenewals}</td>

                    <tr>
                        <td style="color: #003194; font-weight: 700">  Tujuan </td>
                        <td><b> : </b></td>
                         <td>${actionBean.permitItem.kodItemPermit.nama}</td>
                    </tr>

                    <tr>
                        <td style="color: #003194; font-weight: 700">  Tahun </td>
                        <td><b> : </b></td>
                         <td>${actionBean.year}</td>
                    </tr>

                    <tr>
                        <td style="color: #003194; font-weight: 700">  Bayaran </td>
                        <td><b> : </b></td>
                        <td>${actionBean.permohonanTuntutanKos.amaunTuntutan}</td>
                    </tr>

                
       </table>
                </div>
      
             </fieldset>
            </c:if>
</s:form>
