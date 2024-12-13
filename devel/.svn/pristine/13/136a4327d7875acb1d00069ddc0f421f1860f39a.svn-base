<%-- 
    Document   : pertanyaan_cukai_1
    Created on : Nov 16, 2009, 5:06:19 PM
    Author     : nurfaizati
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function change(){
        var a = $("#kod").val();
        var b = a.toUpperCase();

        $("#kod").val(b);
    }

    function filterDaerah(kodDaerah){
        var url = '${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?penyukatanBPM&daerah='+kodDaerah;
        $.get(url,
        function(data){
            $('#daerah').html(data);
        },'html');
    }

</script>

<%--<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikMelakaActionBean">--%>
<div id="daerah">
    <table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pertanyaan Hakmilik</font>
            </div>
        </td>
    </tr>
</table>
    <s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean" id ="pertanyaan_cukai_1">
         

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Carian Hakmilik
                </legend>

                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukan Maklumat Berikut.

                </div>&nbsp;
                <div class="instr" align="center">
                    <s:errors/>
                </div>
                 <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label> No Akaun :</label>
                    <s:text name="akaun.noAkaun" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                 </c:if>

                <p>
                    <label> ID Hakmilik :</label>
                    <s:text name="hakmilik.idHakmilik" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>  Daerah :</label>
                    <s:select name="daerah" style="width:210px;" onchange="filterDaerah(this.value);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />
                    </s:select>
                </p>
                <p>
                    <label>  Bandar/Pekan/Mukim :</label>
                    <s:select name="hakmilik.bandarPekanMukim.kod" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <p>
                    <label for="Id Hakmilik">Kod Lot :</label>
                    <s:select name="hakmilik.lot.kod" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>

                <p>
                    <label for="Id Hakmilik">No Lot/PT :</label>
                    <s:text name="hakmilik.noLot"  maxlength="15" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="Id Hakmilik">Jenis Geran :</label>
                    <s:text name="hakmilik.kodHakmilik.kod" id="kod" size="31" onblur="javaScript:change();" />
                </p>
                <p>
                    <label for="Id Hakmilik">No Hakmilik :</label>
                    <s:text name="hakmilik.noHakmilik"  size="31"/>
                </p>

                <table border="0" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit   name="search" value="Cari" class="btn" />
                            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('pertanyaan_cukai_1');" />
                        </td> </tr>
                </table>
            </fieldset>
        </div>

        <c:if test="${actionBean.flag}">
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Hakmilik
                    </legend>

                    <div class="content" align="center">
                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <display:table class="tablecloth" name="${actionBean.list}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_hakmilik" id="line"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                             <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Pilih" sortable="true">
                            <div align="center"><s:radio name="idHakmilik" value="${line.hakmilik.idHakmilik}" /></div></display:column>
                            <display:column title="Bil" sortable="true"><div align="center">${row_num}</div></display:column>
                             <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column property="noAkaun" title="No Akaun"  />
                             </c:if>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"  />
                            <display:column property="hakmilik.daerah.nama" title="Daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="BandarPekanMukim"  />
                            <display:column property="hakmilik.lot.nama" title="Kod Lot" />
                            <display:column property="hakmilik.noLot" title="No Lot/PT" />
                            <display:column property="hakmilik.kodHakmilik.kod" title="Jenis Geran"  />
                            <display:column property="hakmilik.noHakmilik" title="No Hakmilik" />
                            <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, 0.00}" style="text-align:right" />
                            <display:column title="Status Pembayaran"  style="text-align:right">
                                ${line.baki <= 0 ? "BAYAR" : "BELUM BAYAR" }
                            </display:column>
                            <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"  style="text-align:right"/>

                        </display:table>
                    </div>

                    <table border="0" bgcolor="green" width="100%">
                        <tr>
                            <td align="right">
                                <s:submit name="selectList" value="Terus" class="btn" disabled="${actionBean.btn}"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>

