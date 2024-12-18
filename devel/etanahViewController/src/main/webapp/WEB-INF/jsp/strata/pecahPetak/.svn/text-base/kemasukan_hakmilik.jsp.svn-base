<%-- 
    Document   : kemasukan_hakmilik
    Created on : Nov 8, 2010, 10:08:06 AM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function save(event, f){


        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');




    }

    function onChangePetak(){
        var bilPetakBaru = document.getElementById("bilPetakBaru").value;
        alert("test"+bilPetakBaru);
 
        var url = '${pageContext.request.contextPath}/strata/kemasukanHakmilikPHPC?onChangePetakbaru&bilPetakBaru='+bilPetakBaru;
        alert(url);
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form  name="form1" beanclass="etanah.view.strata.KemasukanHakmilikPHPCActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>
            <p>
                <label>No Hakmilik : </label> ${actionBean.mohonHakmilik.hakmilik.idHakmilik}
            </p>
            <p>
                <label>Luas (meter persegi) : </label>${actionBean.mohonHakmilik.hakmilik.luas}
            </p>
            <p>
                <label>Unit Syer : </label>${actionBean.mohonHakmilik.hakmilik.unitSyer}
            </p>
            <p>&nbsp;</p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pecah Petak</legend>
            <p>
                <label>Bilangan Petak Baru : </label> <s:text id="bilPetakBaru" name="bilPetakBaru2"  onchange="onChangePetak();"></s:text>
            </p>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.listPetakBaru}" cellpadding="0" cellspacing="0" id="line" style="width:80%;"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Petak Baru"> ${line_rowNum}</display:column>
                    <display:column title="Luas Petak (meter persegi)" class="luas"><s:text name="listPetakBaru[${line_rowNum - 1}].luasPetak"></s:text></display:column>
                    <display:column title="Unit Syer"><s:text name="listPetakBaru[${line_rowNum - 1}].unitSyer"></s:text></display:column>
                    <%--<display:column title="Petak Aksesori"><s:button value="Papar" name="petakAks" class="btn"/></display:column>
                    <display:column property="" title="Poskod" />
                    <display:column property="" title="Negeri" />--%>
                </display:table>

                
            </p>
              <div>
                   <legend>Maklumat Petak Aksesori</legend>
                 <p align="center">
 <display:table class="tablecloth" name="${actionBean.listPetakAks}" cellpadding="0" cellspacing="0" id="line" style="width:80%;"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil"> ${line_rowNum}</display:column>
                    <%--<display:column title="ID Petak Aksesori" class="luas" property="idAksesori"></display:column>--%>
                    <display:column title="Nama" property="nama"><%--<s:text name="listPetakAks[${line_rowNum - 1}].nama"></s:text>--%></display:column>
                    <%--<display:column title="Petak Aksesori"><s:button value="Papar" name="petakAks" class="btn"/></display:column>--%>
                    <display:column property="kegunaan.perihal" title="Kegunaan" />
                    <display:column title="Kemaskini"><s:select name="actionBean.petakAksesori.idPetak" style="width:300px;" id="fototimur" onchange="doSetDokumenTimur('${row.timur.dokumen.idDokumen}');">
                    <s:option value="">Sila pilih petak baru</s:option>
                    <s:options-collection collection="${actionBean.listPetakBaru}" label="idPetak" value="idPetak"/>
                </s:select></display:column>
                </display:table>
                 </p>
            </div>

        </fieldset>
           
    </div>
            <p>
           
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Harta Bersama Baru (Jika Ada)</legend>
            <p>
                <label>Luas (meter persegi) : </label> <s:text name="luasHB"></s:text>
            </p>
            <p>
                <label>Jumlah Syer : </label> <s:text name="jumlahSyerHB"></s:text>
            </p>
            <p>
                <label>Perihal Harta Bersama : </label> <s:text name="perihalHB"></s:text>
            </p>

        </fieldset>
    </div><p>
            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
    </p>
            
</s:form>
