<%-- 
    Document   : BorangGH
    Created on : 05-Aug-2010, 15:17:13
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
function refreshPagePenerimaanBorang(){
var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisActionBean">
    <s:messages/>

    <div  id="hakmilik_details">
        <fieldset class="aras1">
            <legend align="center">
                <b>PENERIMAAN NOTIS/BORANG</b>
            </legend>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />

                </display:table>
            </p>
             </fieldset>
         </div>
            <br/>
            <br/>
            <br/>
            
            <c:if test="${actionBean.hakmilik ne null}">
<fieldset class="aras1">
            <legend align="center">
                <b>PENERIMAAN NOTIS/BORANG</b>
            </legend>
    <div  align="center">
                <table>
                    <tr>
                        <td>Perbicaraan Pengambilan No: </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan ne null}">
                                 ${actionBean.hakmilikPerbicaraan.idPerbicaraan}
                             </c:if>
                             <c:if test="${actionBean.hakmilikPerbicaraan.idPerbicaraan eq null}">
                                 <c:out value="Tiada Maklunat" />
                             </c:if>
                          </td>
                    </tr
                    <tr>
                        <td>Tarikh Perbicaraan: </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara ne null}">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${actionBean.hakmilikPerbicaraan.tarikhBicara}" />
                             </c:if>
                             <c:if test="${actionBean.hakmilikPerbicaraan.tarikhBicara eq null}">
                                 <c:out value="Tiada Maklunat" />
                             </c:if>
                          </td>
                    </tr>
                    <tr>
                        <td>No Warta: </td>
                        <td>Tiada Maklumat   </td>
                    </tr>
                    <tr>
                        <td>Tarikh Warta: </td>
                        <td>Tiada Maklumat   </td>
                    </tr>
                </table>

            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonan}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_notis" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah"/>
                    <display:column property="hakmilik.daerah.nama" title="Bandar/Pekan/Mukim"/>
                    <display:column property="kodUnitLuas.nama" title="Luas Dikehendaki"/>

                            <display:column title="Orang yang berkepentingan">
                                    <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                 <br>
                             <c:out value="${count}"/>)
                             <c:out value="${senarai.pihak.nama}"/><br>
                                No.KP/Co :<c:out value="${senarai.pihak.noPengenalan}"/>
                                    <c:set value="${count + 1}" var="count"/>
                        </c:forEach>
                        </display:column>

                        <display:column title="Jenis Kepentingan">
                             <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                 <br>
                             T/Tanah  <c:out value="${senarai.syerPembilang}"/>/<c:out value="${senarai.syerPenyebut}"/>

                            </c:forEach>

                        </display:column>
                        <display:column title="Pengumpukan Award">
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                              <c:set value="${senarai.syerPembilang}" var="a"/>
                              <c:set value="${senarai.syerPenyebut}" var="b"/>
                              <c:set value=" ${actionBean.hakmilikPerbicaraan.penilaiAmaun}" var="c"/>
                              <%--<c:out value="${actionBean.hakmilikPerbicaraan.penilaiAmaun}"/>
                              ${actionBean.hakmilikPerbicaraan.penilaiAmaun}--%>
                             <% try{ %>
                              <br> RM <fmt:formatNumber pattern="#,##0.00" value="${a/b*c}"/>
                             <%-- <br>RM<c:out value="${a/b*c}"/>--%>
                              <%}catch(Exception e){
                                    e.printStackTrace();
                                    }
                                %>
                            </c:forEach>
                        </display:column>
                              <display:column  title="Untuk Kegunaan Pejabat">
                               <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikPerbicaraan.penilaiAmaun}"/>
                              </display:column>

                </display:table>


          <p align="center">
           <s:button name="jana dokumen"  value="Jana Dokumen" class="btn" onclick="doSubmit"/>
           <s:button name="hantar"  value="Hantar" class="btn" onclick="doSubmit"/>
           </p>
            </c:if>
            </div>
    </fieldset>
   
</s:form>

