<%-- 
    Document   : senarai_pelelong_berlesen
    Created on : Apr 8, 2010, 5:46:33 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


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

    function save(event, f){
        if(validatela()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }

    function validatela(){
        var len = ${fn:length(actionBean.listJurulelong)};
        //var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");

            if(ckd == false && i == len){
                alert("Sila Pilih Jurulelong");
                return false;
            }
            if(ckd == true){
                return true;
            }
        }
    }

    function saveN9(event, f){
        if(validatelaN9()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
    }

    function validatelaN9(){
        var len = ${fn:length(actionBean.list)};
        //var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#chkbox'+i).is(":checked");

            if(ckd == false && i == len){
                alert("Sila Pilih Jurulelong");
                return false;
            }
            if(ckd == true){
                return true;
            }
        }
    }
    
    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/Memasukkan_Maklumat_JurulelongBerlesen?refreshPage2';
        $.get(url,
        function(data){
            $('#lelong').html(data);
        },'html');
    }

    function tambah(){
        window.open("${pageContext.request.contextPath}/lelong/JurulelongTab?showForm", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }
    

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

</script>

<s:form beanclass="etanah.view.stripes.lelong.UtilitiSemakanPembidaActionBean">
    <div class="subtitle displaytag">
          <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.idPermohonan}"/>
        <br>
        <fieldset class="aras1">

            <%--for N9 je--%>
            <c:if test = "${actionBean.mlk eq false}">
                <legend>
                    Senarai Jurulelong Berlesen
                </legend><br>
                <em>*</em>Sila Pilih Satu Sahaja JuruLelong<br><br>
                <div class="subtitle displaytag">

                    <display:table class="tablecloth" name="${actionBean.list}" pagesize="10" id="line" requestURI="/lelong/Memasukkan_Maklumat_JurulelongBerlesen">

                        <display:column> <s:radio name="jurulel" id="chkbox${line_rowNum}" value="${line.idSytJlb}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Syarikat" property="nama" class="nama"/>
                        <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama"/>
                        <display:column title="No Pengenalan" property="noPengenalan"/>
                        <display:column title="Alamat">${line.alamat1}
                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                            ${line.alamat2}
                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                            ${line.alamat3}
                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                            ${line.alamat4}</display:column>
                        <display:column title="Poskod" property="poskod" />
                        <display:column title="Negeri" property="negeri.nama"/>
                        <display:column title="Cawangan" property="cawangan.name"/>
                        <display:column title="No Telefon Bimbit" property="noTelefon2"/>
                        <display:column title="No Telefon Syarikat" property="noTelefon1"/>
                    </display:table>
                    <p align="center"><label></label>
                        <br>
                        <s:button name="simpanPemohon" value="Simpan" class="btn" id="add" onclick="saveN9(this.name, this.form);" />
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </div><br>

            </c:if>


            <%--for Melaka je--%>

            <c:if test = "${actionBean.mlk eq true}">
                <legend>
                    Senarai Jurulelong Berlesen
                </legend><br>
                <em>*</em>Sila Pilih Satu Sahaja JuruLelong<br><br>
                <div class="subtitle displaytag">

                    <display:table class="tablecloth" name="${actionBean.listJurulelong}" pagesize="10" id="line" requestURI="/lelong/utilitiSemakanPembida">

                        <display:column> <s:radio name="jurulel" id="chkbox${line_rowNum}" value="${line.idJlb}" class="chkbox" onmouseover="this.style.cursor='pointer';"/></display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama Jurulelong" property="nama" class="nama"/>
                        <display:column title="Jenis Pengenalan" property="jenisPengenalan.nama"/>
                        <display:column title="No Pengenalan" property="noPengenalan"/>
                        <display:column title="Alamat">${line.alamat1}
                            <c:if test="${line.alamat2 ne null}"> , </c:if>
                            ${line.alamat2}
                            <c:if test="${line.alamat3 ne null}"> , </c:if>
                            ${line.alamat3}
                            <c:if test="${line.alamat4 ne null}"> , </c:if>
                            ${line.alamat4}</display:column>
                        <display:column title="Poskod" property="poskod" />
                        <display:column title="Negeri" property="negeri.nama"/>
                        <display:column title="Cawangan" property="cawangan.name"/>
                        <display:column title="No Telefon Bimbit" property="noTelefon2"/>
                        <display:column title="Syarikat" property="sytJuruLelong.nama" class="nama"/>
                        <display:column title="No Telefon Syarikat" property="noTelefon1"/>
                    </display:table>
                    <p align="center"><label></label>
                        <br>
                        <s:button name="simpanPemohonMelaka" value="Simpan" class="btn" id="add" onclick="save(this.name, this.form);" />
                        <%--<s:button name="" value="Daftar Jurulelong Baru" class="longbtn" onclick="tambah();" />--%>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </div><br>

            </c:if>

        </fieldset>
    </div>
</s:form>