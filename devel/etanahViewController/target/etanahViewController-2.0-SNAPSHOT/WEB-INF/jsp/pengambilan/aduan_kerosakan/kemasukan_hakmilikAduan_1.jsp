<%-- 
    Document   : kemasukan_hakmilik
    Created on : 01-Mar-2010, 12:37:19
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function validation() {
        if($("#idH").val() == ""){
            alert('Sila masukkan " ID Hakmilik " terlebih dahulu.');
            $("#idH").focus();
            return true;
        }
    }

    function search2(event, f){
        // if(validation()){

        // }
        // else{
        
        //alert('s1');
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            //alert('s2');
            $('#page_div',opener.document).html(data);
            //alert('s');
            //self.opener.refreshPageHakmilik();
            //alert('sasda');
            //self.opener.refreshPageAduan();
            //$.unblockUI();
            self.close();
        },'html');
        
        //self.opener.refreshPageAduan();
        // }
    }
    
    function save1(event, f){
        // if(validation()){

        // }
        // else{
        
        //alert('s1');
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            //alert('s2');
            $('#page_div',opener.document).html(data);
            //alert('s');
            //self.opener.refreshPageHakmilik();
            //alert('sasda');
            //self.opener.refreshPageAduan();
            //$.unblockUI();
            self.close();
        },'html');
        
        self.opener.refreshPageAduan();
        // }
    }
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
    
    function refreshPageAduan(){
        alert('refreshPageAduan');
        
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/terima_aduan';
        $.get(url,
        function(data){
            $('#page_div').html(data);
            },'html');
    }

    function filterBandarPekanMukim() {
        var kodDaerah = $("#daerah").val();
        alert(kodDaerah);
        $.post('${pageContext.request.contextPath}/pelupusan/utilitiReportNs?senaraiKodBPM&kodDaerah=' + kodDaerah,
        function(data) {
            if (data != '') {
                $('#partialKodBPM').html(data);
                //                    $.unblockUI();
            }
        }, 'html');

    }

    function moduledet(val){
        $.get('${pageContext.request.contextPath}/pengambilan/terima_aduan?findKodSeksyen&kod=' + val,
        function(data){
            $("#kodS").replaceWith($('#kodS', $(data)));
        }, 'html');
    }

   
</script>


<div class="subtitle">
    <%--<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean" >--%>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />s
    <%-- <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatHakmilikActionBean" >  --%>
        
        <s:form beanclass="etanah.view.stripes.pengambilan.aduan.TerimaAduanActionBean" >
        <fieldset class="aras1">
            <legend>
                Kemasukan Hakmilik Aduan 
            </legend>
            <p>
                <label for="Id Hakmilik">ID Hakmilik :</label>
                <s:text name="idH" id="idH" value="${idH}"/> <s:button name="searchHakmilik" value="Cari" class="btn" onclick="search2(this.name, this.form)" />
                <s:text name="idH2" id="idH2" value="${actionBean.idH}"/>
            </p>
            <p>
                <label for="atau"><i><em>atau</em></i></label>
            </p>
            <br>
            <p>
                <label for="No Lot">No Lot :</label>
                
                <s:select name="kodLot" value="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiLotPelupusan}" label="nama" value="kod" />
                </s:select>   
                <s:text name="noLot" id="noLot"/>
            </p>
            <p>
                <label> Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.nama}  ${actionBean.namaDaerah}
                
            </p>
            <p>
                <label> Mukim/Pekan/Bandar :</label>
                <s:select name="bandarPekanMukimBaru" value="${actionBean.kodDaerah}" id="mpb" onchange="moduledet(this.value);">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                   
                </s:select>
            </p>
            <p>
                <label> Seksyen :</label>${actionBean.listKodSeksyen}
                 <s:select name="kodSeksyen" id="kodS"  style="width:40%;" value="kod">
                    <s:option  value="">Sila Pilih</s:option>
                    <c:forEach items="${actionBean.listKodSeksyen}" var="i" >
                        <s:option value="${i.kod}" >${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <p>
                <label>Luas Sebenar :</label>
                <s:text name="luasT" id="luasT"/>
                <s:select name="kodUnit" value="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="H">Hektar</s:option>
                    <s:option value="M">Meter Persegi</s:option>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                
                <s:button name="saveHakmilik" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


    </s:form>
</div>