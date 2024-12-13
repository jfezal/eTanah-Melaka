<%-- 
    Document   : carian_notis
    Created on : Apr 10, 2010, 4:45:00 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doSearch(f, e){
        if($('#idPerserahan').val() == '' && $('#idHakmilik').val()=='' && $('#idNotis').val() == '' && $('#jenisNotis').val() ==''){
            alert('Sila isi salah satu daripada yg berikut:\n Id Perserahan atau\n Id hakmilik atau\n Id notis atau\n Jenis notis');
            return;
        }

        var k = $('#idPerserahan').val() && $('#idHakmilik').val() && $('#idNotis').val() && $('#jenisNotis').val();
        if(k == '0') {
            alert('Sila Pilih Notis');
            $('#idPerserahan').val() && $('#idHakmilik').val() && $('#idNotis').val() && $('#jenisNotis').val().focus();
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
    <s:form beanclass="etanah.view.daftar.utiliti.PertanyaanNotisActionBean">
        <span class=instr>Sila masukan salah satu daripada yang berikut.</span><br/>
        <fieldset class="aras1">
            <legend>Carian Notis</legend>
            <p>
                <label>ID Perserahan :</label>
                <s:text name="idPerserahan" id="idPerserahan" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>            
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="idHakmilik" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p> 
            <%--<p>
                <label>ID Notis :</label>
                <s:text name="idNotis" id="idNotis" onkeyup="this.value=this.value.toUpperCase();"/><em>atau</em>
            </p>--%>
            <p>
                <label>Jenis Notis :</label>
                <s:select name="jenisNotis" id="jenisNotis">
                    <s:option value="">Sila Pilih</s:option>                    
                    <s:option value="19A">Borang 19A</s:option>
                    <s:option value="19C">Borang 19C</s:option>
                    <s:option value="19F">Borang 19F</s:option>
                    <s:option value="16H">Notis 2A</s:option>
                    <s:option value="2B">Notis 2B</s:option>
                    <s:option value="5A">Notis 5A</s:option>
                    <s:option value="5F">Notis 5F</s:option>
                    <s:option value="6A">Notis 6A</s:option>
                    <s:option value="8A">Notis 8A</s:option>
                    <s:option value="">Lain-Lain Notis</s:option>
                </s:select>
            </p> 
            <p>
                <label>&nbsp;</label>
               <%-- <s:submit name="searchNotis1" value="Carian" class="btn"/>
                <s:button name="searchNotis" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>--%>
                <s:button name="searchNotis1" value="Cari" class="btn" onclick="doSearch(this.form, this.name);"/>
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
                <display:table style="width:100%" class="tablecloth" name="${actionBean.listMap}" pagesize="10"
                               cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/daftar/pertanyaan_notis?searchNotis1" excludedParams="searchNotis1">


                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="ID Permohonan">${line.idMohon}</display:column>
                    <display:column title="Urusan">${line.urusan}</display:column>
                    <display:column title="ID Hakmilik">${line.idHakmilik}</display:column>
                    <display:column title="No. Lot">${line.noLot}</display:column>
                    <display:column title="Jenis Lot">${line.namaLot}</display:column>
                    <display:column title="Borang">${line.kodNotis}</display:column>
                    <display:column title="Tarikh Notis">${line.trhNotis}</display:column>
                      
                
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
