<%-- 
    Document   : jadual_tanah_kelompok
    Created on : Nov 9, 2011, 1:00:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    
    $(document).ready(function() {
        var a = $('#pilihCarian').val() ;
        if(a == "id"){
            $('#idP').show() ;
            $('#sb').hide() ;
            $('#idH').hide();
        }
        else if(a == "sbb"){
            $('#sb').show();
            $('#idP').hide();
            $('#idH').hide();
        }
        else if(a == "hakmilik"){
            $('#sb').hide();
            $('#idP').hide();
            $('#idH').show();
            
        }
        else {
            $('#idP').hide();
            $('#sb').hide() ;
            $('#idH').hide();
            
        }
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).addClass("hyper");
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pelupusan/jadualTanahKelompok?popHakmilik&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
            
        }
        var len_ = $(".daerah_").length;

        for (var j=0; j<=len_; j++){
            $('.hakmilik_'+j).addClass("hyper");
            $('.hakmilik_'+j).click( function() {
                window.open("${pageContext.request.contextPath}/pelupusan/jadualTanahKelompok?popHakmilik&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }


    });
    function validate(){
        if($('#pilihCarian').val() == ""){
            alert("Sila pilih pilihan carian anda");
            return false ;
        }
        return true ;
    }
    function refreshjadualtanahKelompok(){
        var url = '${pageContext.request.contextPath}/pelupusan/jadualTanahKelompok?refreshPage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function pilih(){
        var a = $('#pilihCarian').val() ;
        if(a == "id"){
            $('#idP').show() ;
            $('#sb').hide() ;
            $('#idH').hide();
        }
        else if(a == "sbb"){
            $('#sb').show();
            $('#idP').hide();
            $('#idH').hide();
        }
        else if(a == "hakmilik"){
            $('#sb').hide();
            $('#idP').hide();
            $('#idH').show();            
        }
        else {
            $('#idP').hide();
            $('#sb').hide() ;
            $('#idH').hide();            
        }
        $('#idP').value="";
        $('#sb').value="";
        $('#idH').value="";
    }
    
    function selectCheckBox(f)
    {
        var e= $('#sizePermohonanGSA').val();
        var id = $('#idPermohonanDigunakan').val();
        //                alert(e);
        //                alert(id);
        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {
            if(e=='1'){
                if(document.frm.checkmate.checked) {
                    //                    alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate.value ;
                }

            }
            else {
                if(document.frm.checkmate[cnt].checked) {
                    //                  alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/jadualTanahKelompok?pilihTanah&item='+data,q,
            function(data){
                $('#page_div').html(data);
                self.refreshjadualtanahKelompok();
            }, 'html');
            
        }
    }

    function deleteRowHakmilik(idHakmilikPermohan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/jadualTanahKelompok?deleteRow&idHakmilikPermohan='+idHakmilikPermohan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
</script>
<style type="text/css">

    .hyper{

        text-decoration: underline;
        cursor: hand;
    }

</style>
<s:form beanclass="etanah.view.stripes.pelupusan.JadualTanahBerkelompok" name="frm">
    <s:messages/>
    <s:errors/>
    <c:if test="${cari}">
        <fieldset class="aras1">
            <p>
                <label>Carian menggunakan :</label>
                <s:select name="pilihCarian" id="pilihCarian" onchange="pilih();">
                    <s:option value="">Sila Pilih</s:option>                
                    <s:option value="id">Id Permohonan</s:option>
                    <s:option value="sbb">Agensi Pembangunan Persekutuan/Negeri</s:option>
                    <s:option value="hakmilik">Id Hakmilik</s:option>
                </s:select>          
            </p>
            <p id="idH">
                <label>Id Hakmilik :</label>
                <s:text name="idHakmilikDigunakan" id="idHakmilikDigunakan"/>
            </p>
            <p id="idP">
                <label>Id Permohonan :</label>
                <s:text name="idPermohonanDigunakan" id="idPermohonanDigunakan"/>
            </p>
            <p id="sb">
                <label>Agensi Pembangunan Persekutuan/Negeri :</label>
                <s:text name="sebab"/>
            </p>
            <p>
                <label>&nbsp;</label>

                <s:button  name="carianPermohonan" id="carianPermohonan" value="Cari" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
            </p>
        </fieldset>
    </c:if>
    <fieldset class="aras1">
        <c:if test="${gsa}">
            <legend>
                Hakmilik Permohonan GSA
            </legend>
            <%--<s:hidden name="sizePermohonanGSA" id="sizePermohonanGSA" value="${actionBean.sizehakmilikPermohonanListPermohonanGSA}"/>--%>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanListPermohonanGSA}" cellpadding="0" cellspacing="0"
                               requestURI="/pelupusan/jadualTanahKelompok" id="line">
                    <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.hakmilik.idHakmilik}"/></display:column>
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                </display:table>
            </div>
        </c:if>
        <div align="center">
            <c:if test="${gsa}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                    <s:button name="pilihTanah" value="Pilih" class="btn" onclick="javascript:selectCheckBox(this.form);"/>
                    <s:button name="carianTanahSemula" value="Cari Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' and actionBean.stageId eq '01Kemasukan'}">
                    <s:button name="pilihTanah" value="Pilih" class="btn" onclick="javascript:selectCheckBox(this.form);"/>
                    <s:button name="carianTanahSemula" value="Cari Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </c:if>
        </div>
    </fieldset>
    <fieldset class="aras1">
        <c:if test="${gsa}">
            <legend>
                Hakmilik Penamatan GSA 
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanListPenamatanGSA}" cellpadding="0" cellspacing="0"
                               requestURI="/pelupusan/jadualTanahKelompok" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik_${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah_" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                        <display:column title="Hapus"><a onclick="deleteRowHakmilik(${line.id},this.form)" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="15px" width="15px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a></display:column>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' and actionBean.stageId eq '01Kemasukan'}">
                            <display:column title="Hapus"><a onclick="deleteRowHakmilik(${line.id},this.form)" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="15px" width="15px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a></display:column>
                        </c:if>
                    </display:table>
            </div>                     
        </c:if>
    </fieldset>             
</s:form>
