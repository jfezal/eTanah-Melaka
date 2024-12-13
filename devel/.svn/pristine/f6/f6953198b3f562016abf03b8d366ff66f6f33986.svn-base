<%-- 
    Document   : pindahan_akaun_deposit
    Created on : Apr 17, 2011, 11:23:13 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function generateReportArchive(f){

        var form = $(f).formSerialize();
        var kodNegeri = document.getElementById("negeri");
        var report = null;
        if(kodNegeri.value == "melaka"){
            report = 'HSL_R_40_MLK.rdf';
        }else{
            report = 'HSL_R_37.rdf';
        }
        var param = document.getElementById('cawanganAchive');

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_kod_caw="+param.value+"&report_p_kod_daerah="+param.value, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function test123(selectInput, i){
        alert(selectInput);
        alert(i);
    }

    function selectAll(a){
                for (i = 0; i < 100; i++){
                    var c = document.getElementById("no_akaun" + i);
                    if (c == null) break;
                    c.checked = a.checked;
                }
            }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Deposit Yang Tidak Dituntut</font>
            </div>
        </td>
    </tr>
</table></div>
<stripes:errors />
<stripes:messages/>
<p></p>
<stripes:form beanclass="etanah.view.stripes.hasil.PindahanAkaunDepositActionBean" id="pindahan_deposit" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
     <div class="subtitle">
         <fieldset class="aras1">
             <legend>Carian Akaun Deposit</legend>
             <p></p>
             <p>&nbsp;&nbsp;&nbsp
                 Sila Nombor Akaun Deposit untuk membuat carian.<br>
                 <%--<label><em>*</em>No. Fail/ Rujukan :</label>--%>
                 <label>Nombor Akaun Deposit :</label>
                 <stripes:text name="noAkaun" id="fail"/>
             </p>
             <p>
                 <label>Cawangan :</label>
                 <stripes:select name="daerah" id="daerah" style="width:210px;">
                     <stripes:option value="">--Sila Pilih--</stripes:option>
                     <c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                         <stripes:option value="${i.kod}">${i.kod} - ${i.nama}</stripes:option>
                     </c:forEach>
                 </stripes:select>
             </p>
             <p>
                 <label>&nbsp;</label>
                 <stripes:submit name="Step2" class="btn" value="Cari"/>
                 <stripes:button name="" value="Isi Semula" class="btn" onclick="clearText('pindahan_deposit');"/>
             </p>
             <p></p>
         </fieldset>
     </div>
     <p></p>
     <c:if test="${actionBean.flag}">
         <div class="subtitle">
             <fieldset class="aras1">
                 <legend>Hasil Carian Akaun Deposit</legend>
                 <p></p>
                 <div align="center">
                     <display:table name="${actionBean.senaraiAkaun}" id="row" class="tablecloth">
                         <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                         <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                             <stripes:checkbox name="chkbox[${row_rowNum - 1}]"
                                               value="${row.noAkaun}" id="no_akaun${row_rowNum - 1}"/>
                         </display:column>
                         <display:column title="Nama Pendeposit" property="pemegang.nama"/>
                         <display:column property="infoAudit.tarikhMasuk" style="text-align:center" title="Tarikh" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                         <display:column title="Cawangan" property="cawangan.name"/>
                         <display:column title="Nombor Akaun Deposit" property="noAkaun"/>
                         <display:column title="Amaun (RM)" class="number" property="baki" format="{0,number, 0.00}" style="text-align:right"/>
                         <display:column title="Status">
                            ${row.status.kod == 'A' ? 'Aktif' : 'Tidak'}
                        </display:column>
                     </display:table >
                 </div>
                 <p></p>
             </fieldset>
         </div>
         <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr><td align="right"><stripes:submit name="Step3" class="btn" value="Seterusnya"/></td></tr>
        </table></div>
     </c:if>
</stripes:form>