<%-- 
    Document   : carian_agensi
    Created on : Nov 23, 2012, 12:26:38 PM
    Author     : hazirah
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doSearch(f, e){
        if($('#idMohon').val() == '' && $('#idHakmilik').val()=='' && $('#noKP').val() == '' && $('#nama').val() =='' && $('#jenisAgensi').val() ==''){
            alert('Sila isi salah satu daripada yg berikut:\n Id Mohon atau\n Id hakmilik atau\n No I/C atau\n Nama atau\n Agensi');
            return;
        }

        var k = $('#idMohon').val() && $('#idHakmilik').val() && $('#noKP').val() && $('#nama').val() && $('#jenisAgensi').val();
        if(k == '0') {
            alert('Sila Pilih Agensi');
            $('#idMohon').val() && $('#idHakmilik').val() && $('#noKP').val() && $('#nama').val() && $('#jenisAgensi').val().focus();
            return;
        }

        <%--else if ($('#jenisNotis').val() ==''){--%>
            <%--alert('Sila Pilih Jenis Notis.');--%>
            <%--return;--%>
        <%--}--%>
        f.action = f.action + '?' + e;
        f.submit();
    }

    function clearForm(f) {
        $(f).clearForm();
    }
</script>


<div class="subtitile">

    <s:errors/>
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.pengambilan.CarianAgensiActionBean">
        <span class=instr>Sila masukan salah satu daripada yang berikut.</span><br/>
        <fieldset class="aras1">
            <legend>Carian Agensi</legend>
            <p>
                <label>ID Mohon :</label>
                <s:text name="idMohon" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>
            <p>
                <label>No I/C :</label>
                <s:text name="noKP" id="noKP" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>
            <p>
                <label>Nama :</label>
                <s:text name="nama" id="nama" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>
            <p>
                <label>Agensi :</label>

                <s:select name="jenisAgensi" id="jenisAgensi">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="Mahkamah">Mahkamah</s:option>
                    <s:option value="Y">Amanah Raya</s:option>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
               <%-- <s:submit name="searchNotis1" value="Carian" class="btn"/>
                <s:button name="searchNotis" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>--%>
                <%--<s:button name="searchNotis1" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>--%>
                <%--<s:button name="searchAgensi1" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>--%>
                <s:button name="searchAgensi2" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>
                <s:button name="reset" value="Isi Semula" onclick="clearForm(this.form);" class="btn"/>
            </p>
        </fieldset>
        <br/>


       <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Carian
            </legend>
            <div class="content" align="center">

                <%--<display:table style="width:100%" class="tablecloth" name="${actionBean.listMap}" pagesize="10"
                               cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/pengambilan/carian_agensi?searchAgensi1" excludedParams="searchAgensi1">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="ID Mohon">${line.idMohon}</display:column>
                    <display:column title="ID Hakmilik">${line.idHakmilik}</display:column>
                    <display:column title="Tuan Tanah">${line.nama}</display:column>
                    <display:column title="Jumlah Pampasan">${line.pampasan}</display:column>

                </display:table>--%>

                <display:table style="width:100%" class="tablecloth" name="${actionBean.listAmbilPampasan}" pagesize="10"
                               cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/ambil/carian_agensi?searchAgensi2" excludedParams="searchAgensi2">

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="ID Mohon">${line.perbicaraanKehadiran.pihak.permohonan.idPermohonan}</display:column>
                    <display:column title="ID Hakmilik">${line.perbicaraanKehadiran.perbicaraan.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>
                    <display:column title="Tuan Tanah">${line.perbicaraanKehadiran.pihak.pihak.nama}</display:column>
                    <display:column title="Jumlah Pampasan">${line.jumTerimaPampasan}</display:column>
                </display:table>

            </div>
            <br/>

        </fieldset>
    </div>


        <%--<c:if test="${!empty actionBean.map}">
            <fieldset class="aras1">
                <legend>Keputusan Carian</legend>
                <p>
                    <label>ID Notis :</label>
                    ${actionBean.map.idDokumen}
                </p>
                <p>
                    <label>Perserahan :</label>
                    ${actionBean.map.idMohon}
                </p>
                <p>
                    <label>Urusan :</label>
                    ${actionBean.map.urusan}
                </p>
                <p>
                    <label>ID Hakmilik :</label>
                    ${actionBean.map.idHakmilik}
                </p>
                <br/>
                <p>
                    <label>Borang :</label>
                    ${actionBean.map.kodDokumen}&nbsp;
                </p>
                <p>
                    <label>Tarikh Notis :</label>
                    ${actionBean.map.trhNotis}
                </p>
                <p>
                    <label>Tarikh Lupus Notis :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Bilangan Salinan :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Diambil :</label>
                    &nbsp;
                </p>
                <p>
                    <label>Tarikh Notis Diserah/Warta:</label>
                    &nbsp;
                </p>
                <p>
                    <label>Penerima Notis :</label>
                    &nbsp;
                </p>
            </fieldset>

        </c:if>--%>
    </s:form>
</div>

