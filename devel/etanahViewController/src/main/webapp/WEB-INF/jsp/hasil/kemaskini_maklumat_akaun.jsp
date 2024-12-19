<%-- 
    Document   : kemaskini_maklumat_akaun
    Created on : Apr 5, 2011, 11:18:58 AM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head><title>Kemaskini Maklumat Akaun</title>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">  
    function submit1(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        f.submit();
    }
</script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    </head>
    <body>
<div id="daerah">
    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kemaskini Maklumat Cukai</font>
                    </div>
                </td>
            </tr>
        </table></div>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
            width="50" height="50" style="display: none" alt=""/>
        <s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_akaun">

        <div class="subtitle">
            <s:errors/>
            <s:messages/>
            <s:text name="pengguna.kodCawangan.kod" value="${actionBean.pengguna.kodCawangan.kod}" style="display:none;" id="pgunaCaw"/>

            <fieldset class="aras1">
                <legend>
                    Carian Hakmilik
                </legend>
                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukan Maklumat Berikut.
                </div>&nbsp;
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <p>
                        <label> No Akaun :</label>
                        <s:text name="noAkaun" maxlength="20" size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>
                <p>
                    <label >ID Hakmilik :</label>
                    <s:text name="idHakmilik"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>  Daerah :</label>
                    <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value);">
                        <s:option value="">--Sila Pilih--</s:option>
                         <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >--%>
                        <c:forEach items="${actionBean.senaraiKodDaerah}" var="i" >
                            <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>

                <p>
                    <label>  Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                            <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
                <p>
                    <label>  Status Hakmilik :</label>
                    <s:select name="kodStatusHakmilik" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStatusHakmilik}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <p>
                    <label >Jenis Hakmilik :</label>
                    <s:text name="kodHakmilik" id="kod" size="31" onblur="javaScript:change();" />
                </p>
                <p>
                    <label >No Hakmilik :</label>
                    <s:text name="noHakmilik"  size="31"  onblur="completeId(this.value)" id="noHakmilik" maxlength="8"/>
                </p>
                <p>
                    <label >Kod Lot :</label>
                    <s:select name="lot" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                <p>
                    <label >No Lot/PT :</label>

                    <s:text name="noLot" id="noLot"  maxlength="15" size="31" onblur="zeroPad(this.value,7);"/>

                </p>

                <div align="right">
                    <s:submit name="Step2" value="Cari" class="btn" onclick="javaScript:submit1(this.form);"/>
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('kemaskini_akaun');" />
                </div>
            </fieldset>

        </div>
        <c:if test="${actionBean.flag}">
            <br>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Hakmilik
                    </legend>

                    <c:set var="row_num" value="${actionBean.__pg_start}"/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listAkaun}"
                                       pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="/hasil/kemaskini_data" id="line"
                                       sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                            <display:column title="Pilih">
                                <div align="center"><s:radio name="rbtAkaun" value="${line.noAkaun}" onclick="nextFunc('${line.hakmilik.daerah.kod}')"/></div>
                            </display:column>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <display:column property="noAkaun" title="No Akaun"  />
                            </c:if>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"  />
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="BandarPekanMukim"  />
                            <display:column property="pemegang.nama" title="Nama Pembayar"  />
                            <display:column title="Nama Pemilik Tanah" >
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">

                                    <c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                                  senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                                  senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                                  senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                                  senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS')
                                                  and senarai.aktif eq 'Y'}">
                                        <c:if test="${actionBean.noPengenalan eq null && actionBean.namaPemilik eq null}">
                                            <c:out value="${senarai.pihak.nama}" /><br>
                                        </c:if>
                                        <c:if test="${actionBean.noPengenalan ne null || actionBean.namaPemilik ne null}">
                                            <c:if test="${actionBean.namaPemilik eq senarai.pihak.nama || actionBean.noPengenalan eq senarai.pihak.noPengenalan}">
                                                <c:out value="${senarai.pihak.nama}" /><br>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="No Pengenalan">
                                <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                                    <c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                                  senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                                  senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                                  senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                                  senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS')
                                                  and senarai.aktif eq 'Y'}">
                                        <c:if test="${actionBean.noPengenalan eq null && actionBean.namaPemilik eq null}">
                                            <c:out value="${senarai.pihak.noPengenalan}" /><br>
                                        </c:if>
                                        <c:if test="${actionBean.noPengenalan ne null || actionBean.namaPemilik ne null}">
                                            <c:if test="${actionBean.namaPemilik eq senarai.pihak.nama || actionBean.noPengenalan eq senarai.pihak.noPengenalan}">
                                                <c:out value="${senarai.pihak.noPengenalan}" /><br>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column property="hakmilik.noLot" title="No Lot/PT" />
                            <display:column property="hakmilik.kodHakmilik.kod" title="Jenis Hakmilik"  />
                            <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, 0.00}" style="text-align:right" />
                            <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"  style="text-align:right"/>
                            <%--<display:column title="Papar"><s:button name="papar" value="Papar" class="btn" onclick="doSubmit('papar',this.form,'${line.hakmilik.idHakmilik}')"/></display:column>--%>
                        </display:table>
                        <p></p>
                        <div align="center"><table style="width:99.2%" bgcolor="green">
                                <tr>
                                    <td align="right">
                                        <s:submit name="Step3" value="Seterusnya" id="nextBtn" class="btn"/>
                                    </td>
                                </tr>
                            </table></div>
                    </div>

                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        dialogInit('Carian Hakmilik');
        $('#nextBtn').attr("disabled", "true");
    });
    function zeroPad(num,count)
    {
        var numZeropad = num + '';
        while(numZeropad.length < count) {
            numZeropad = "0" + numZeropad;
        }
        return numZeropad;
        $("#noLot").val(numZeropad);
    }
    function change(){
        var a = $("#kod").val();
        var b = a.toUpperCase();

        $("#kod").val(b);
    }

    function nextFunc(caw){
        var cawPguna = document.getElementById('pgunaCaw');
         if(cawPguna.value == caw || cawPguna.value == 00){ 
            $('#nextBtn').removeAttr("disabled");}
        else{
            alert("Tidak dibenarkan untuk mengemaskini Akaun daerah lain.");}
    }

<%--    function filterDaerah(kodDaerah){
        alert(kodDaerah);
        var url = '${pageContext.request.contextPath}/hasil/kemaskini_data?penyukatanBPM&daerah='+kodDaerah;
        $.get(url,
        function(data){
            $('#daerah').html(data);
        },'html');
    }--%>
</script>
<script type="text/javascript">
    function completeId(id){
        var l = id.length;
        var lengthId = 8;
        var i = "";
        for(var x=0;x<(lengthId-l);x++){
            i = i+'0';
        }
        var noHakmilik = i+id;
        $("#noHakmilik").val(noHakmilik);
    }
</script>
<script type="text/javascript">
    function filterDaerah(kodDaerah){
        var url = '${pageContext.request.contextPath}/hasil/kemaskini_data?penyukatanBPM&daerah='+kodDaerah;
        $.get(url,
        function(data){
            $('#daerah').html(data);
        },'html');
    }
</script></body>
</html>
