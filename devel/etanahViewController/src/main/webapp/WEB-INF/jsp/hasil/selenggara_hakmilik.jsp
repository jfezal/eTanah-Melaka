<%-- 
    Document   : selenggara_hakmilik
    Created on : 30 Mac 2010, 4:43:28 PM
    Author     : abu.mansur
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function show(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function filterDaerah(kodDaerah){
        var url = '${pageContext.request.contextPath}/hasil/penyelenggaraan_hakmilik?penyukatanBPM&daerah='+kodDaerah;
        $.get(url,
        function(data){
            $('#daerah').html(data);
        },'html');
    }

    function clck(x, baki){
        $('#idHm').val(x);
        $('#balance').val(baki);
    }

    function checkBalance(){
        var a = document.getElementById('idHm');
        var bal = document.getElementById('balance');
        if((bal.value) > 0){
            return true;
        }else{
            if(!confirm("Cukai Tanah bagi Hakmilik "+a.value+" telah dijelaskan. Adakah anda ingin meneruskan tugasan?")){
                return false;
            }
            return true;
        }
    }
</script>
<div id="daerah">
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Penyelenggaraan Hakmilik</font>
                </div>
            </td>
        </tr>
    </table></div>
    <s:form beanclass="etanah.view.stripes.hasil.SelenggaraHakmilikActionBean" id ="selenggara_hakmilik">
        <s:messages />
        <s:errors />

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Carian Hakmilik
                </legend>
                <%--<div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukkan Salah Satu Daripada Maklumat Berikut.
                </div>&nbsp;--%>
                <p>
                    <label> ID Hakmilik :</label>
                    <s:text name="idHakmilik" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
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
                    <label for="Id Hakmilik">Nombor Lot/PT :</label>
                    <s:text name="hakmilik.noLot" maxlength="15" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="Id Hakmilik">Jenis Geran :</label>
                    <s:select name="hakmilik.kodHakmilik.kod" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <%--<s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod" sort="nama" />--%>
                         <c:forEach items="${actionBean.senaraiKodHakmilik}" var="i" >
                            <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                         </c:forEach>
                    </s:select>
                </p>
                <p>
                    <label for="Id Hakmilik">Nombor Hakmilik :</label>
                    <s:text name="hakmilik.noHakmilik" maxlength="8" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p align="right">
                    <s:submit name="search" value="Cari" class="btn" />
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('selenggara_hakmilik');" />
                </p>

                <%--<table border="0" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit name="search" value="Cari" class="btn" />
                            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('selenggara_hakmilik');" />
                        </td> </tr>
                </table>--%>
            </fieldset>
        </div>
                <s:text name="" id="balance" style="display:none"/>
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
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/penyelenggaraan_hakmilik" 
                                       id="line"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Pilih" sortable="true">
                                <div align="center"><s:radio name="idHakmilik" value="${line.hakmilik.idHakmilik}" id="idHm" onclick="clck(this.value,'${line.baki}')"/></div></display:column>
                            <display:column title="Bil" sortable="true">${row_num}</display:column>
                            <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup idhakmilik${line_rowNum}" />--%>
                            <display:column title="ID Hakmilik"><a href="#" onclick="show('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                            <%--<display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="${line_rowNum}" />--%>
                            <display:column property="hakmilik.noLot" title="No Lot/PT" class="popup idhakmilik${line_rowNum}" />
                            <display:column property="hakmilik.kodHakmilik.kod" title="Jenis Geran" class="popup idhakmilik${line_rowNum}" />
                            <display:column property="hakmilik.noHakmilik" title="No Hakmilik" class="popup idhakmilik${line_rowNum}" />
                            <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, 0.00}" style="text-align:right" />
                            <display:column title="Status">
                                ${line.baki <= 0 ? "BAYAR" : "BELUM BAYAR" }
                            </display:column>
                            <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"  style="text-align:right"/>
                        </display:table>
                    </div>
                    <div align="center"><table style="width:99.2%" bgcolor="green">
                        <tr>
                            <td align="right">
                                <s:submit name="selectList" value="Terus" class="btn" disabled="${actionBean.btn}" onclick="return checkBalance();"/>
                            </td>
                        </tr>
                        </table></div>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>