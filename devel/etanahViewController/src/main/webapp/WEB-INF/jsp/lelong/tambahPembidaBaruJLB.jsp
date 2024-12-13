<%-- 
    Document   : tambahPembidaBaruJLB
    Created on : Apr 27, 2012, 3:55:20 PM
    Author     : mazurahayati.yusop, nur.aqilah
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script> 
    $(document).ready(function() {
        $("#simpan").click(function(evt) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var mohonId=$('#idPermohonan').val();     
            var q = $("form").serialize();
            var url = '?' + $(this).attr("name");
    
            $.post(url, q,function(data) {
                if(data =='1'){
                    alert("Maklumat Telah berjaya di simpan");
                    self.close();
                }

                self.opener.refreshingPagingFolder(mohonId);
                
            });
        });
    });

    $(document).ready(function() {
        $("#tarikPermohonan").hide();
        $("#nokp2").hide();
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    function validateNewICLength(value){
        var plength = value.length;
        if(plength != 12){
            alert('No Kad Pengenalan yang dimasukkan salah.');
            $('#noPengenalan').val("");
            $('#noPengenalan').focus();
        }
    }

    function validation() {
        if($("#nama").val() == ""){ 
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return false;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return false;
        }

        if($("#jenis").val() == "B"){
            if($("#noPengenalan").val() == ""){
                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
                $("#noPengenalan").focus();
                return false;
            }
        }
//        if($("#jenis").val() != "B"){
//            if($("#nokp2").val() == ""){
//                alert('Sila masukkan " Nombor Pengenalan " terlebih dahulu.');
//                $("#nokp2").focus();
//                return false;
//            }
//        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#telefon').val("");
            $('#telefon').focus();
        }
    }


    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#noPengenalan").hide();
            $("#noPengenalan").show();
        }else{
            $("#noPengenalan").show();
            $("#noPengenalan").hide();
        }
    }
    
    function validateIC(icno,idPermohonan){

        $.get("${pageContext.request.contextPath}/lelong/senaraipembidaJLB?doCheckIC&icno=" + icno,"idPermohonan=" +idPermohonan,
        function(data){
            if(data =='1'){
                //                alert("Kad Pengenalan No " + icno + " telah wujud!");
                alert("Kad Pengenalan No " + icno + " telah di Senarai Hitamkan");
                $("#noPengenalan").val("");
                $("#noPengenalan").focus();
                return false;
            }
        });
        return true;
    }
</script>


<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="pemohon" id="pemohon"  beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="pihak.idPihak"/>

    <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:hidden id="idLelong" name="idLelong" value="${actionBean.idLelong}"/>
    <c:set value="${actionBean.permohonan.idPermohonan}" var="permohonan.idPermohonan"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembida: ${actionBean.permohonan.idPermohonan} 

                <div class="content">

                    <p>
                        <label><font color="red">*</font> Nama :</label>
                            <s:text id="nama" name="pihak.nama" onchange="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label><font color="red">*</font>Jenis Pengenalan : </label>
                            <s:select id="jenis" name="kodJenis" style="width:170px;" onchange="changeNOKP(this.value);">
                                <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label> <font color="red">*</font>Nombor Pengenalan : </label>
                            <s:text id="noPengenalan" name="noPengenalan" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateIC(this.value,'${actionBean.permohonan.idPermohonan}'), validateNewICLength(this.value);" style="width:170px;"/>
                            <s:text id="noPengenalan" name="noPengenalan"  onchange="this.value=this.value.toUpperCase();" style="width:170px;"/>
                    </p>
                    <p>
                        <label>No. Bank Draf: </label>
                        <s:text id="noRujukan" name="pembida.noRujukan" onchange="this.value=this.value.toUpperCase();" style="width:170px;"/>
                    </p>

                    <p>
                        <label> Nombor Telefon Bimbit: </label>
                        <s:text name="pihak.noTelefonBimbit" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:170px;"/>&nbsp;
                    </p>

                    <c:if test="${same eq false}">
                        <p>
                            <label><font color="red">* </font>ID Hakmilik :</label>
                                <s:select id="idHakmilik" name="idHakmilik" style="width:170px;">
                                    <s:option value="null">-Sila Pilih-</s:option>
                                <s:options-collection collection="${actionBean.listLelongHakmilik}" label="hakmilikPermohonan.hakmilik.idHakmilik" value="hakmilikPermohonan.hakmilik.idHakmilik" />
                            </s:select>
                            &nbsp;
                        </p>
                    </c:if>

                    <c:if test="${same eq true}">
                        <p>
                            <label>ID Hakmilik :</label>
                            <s:textarea id="idHakmilik"  name="idHakmilik" value="hakmilikPermohonan.hakmilik.idHakmilik" readonly="true" rows="2" cols="23" />&nbsp;
                        </p>
                    </c:if>



                </div>
        </fieldset>
    </div>
    <div class="content" align="center">
        <c:if test="${same eq false}">
            <p>
                <s:button name="simpanTambahPembida" id="simpan" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')" />
                <%--<s:button name="simpanTambahPembida" id="simpan" value="Simpan" class="btn" onclick="doSubmit(${actionBean.permohonan.idPermohonan}, this.name)"  />--%>
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close(${actionBean.idLelong})"/>
            </p>
        </c:if>

        <c:if test="${same eq true}">
            <p>
                <s:button name="simpanTambahPembidaBersama" id="simpan" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:button name="simpanTambahPembidaBersama" id="simpan" value="Simpan" class="btn" onclick="doSubmit(${actionBean.permohonan.idPermohonan}, this.name)"/>--%>
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close(${actionBean.idLelong})"/>
            </p>
        </c:if>

    </div><br>

</s:form>

