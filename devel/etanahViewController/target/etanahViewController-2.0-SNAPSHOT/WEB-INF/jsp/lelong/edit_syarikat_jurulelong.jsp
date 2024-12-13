<%-- 
    Document   : edit_syarikat_jurulelong
    Created on : Jun 7, 2013, 12:01:52 PM
    Author     : nur.aqilah
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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
    function save1(){
                
        var idSyarikat=$('#idSyarikat').val(); 
        var idjlb=$('#idjlb').val();
             
        //                    self.opener.refreshPagesAddress123(f);
        self.opener.refreshPagesAddress123(idSyarikat,idjlb);
        self.close();
    }        
    
    /**$(document).ready(function() {
        $("#simpan").click(function(evt) {
            var idSyarikat=$('#idSyarikat').val(); 
            var idjlb=$('#idjlb').val();     
            var q = $("form").serialize();
            var url = '?' + $(this).attr("name");
    
            $.post(url, q,function(data) {
			alert(data);
				var x = validate();
				alert(x);
				if(x){
					self.opener.refreshPagesAddress123(idSyarikat,idjlb);
					self.close();
					}
            });
        });
    });**/
    
    //    $(document).ready(function() {
    //        alert ('ss');
    //        $("#save").click(function(evt) {
    //            var idSyarikat=$('#idSyarikat').val();     
    //            var q = $("form").serialize();
    //            var url = '?' + $(this).attr("name");
    //    
    //            $.post(url, q,function(data) {
    //
    //                self.opener.refreshingPagingFolder(idSyarikat);
    //                self.close();
    //            });
    //        });
    //    });
            
    var DELIM = "__^$__";
    function simpanPihak(event, f){
        if(validation()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                if (data == null || data.length == 0){
                    alert("Maklumat tidak dijumpai");
                    return;
                }
                var p = data.split(DELIM);
                $('#idSyarikat', opener.document).append('<option value="'+p[2]+'">'+p[0]+'</option>');
                $('#idSyarikat option[value='+p[2]+']', opener.document).attr('selected','selected');
                $('#noSykt',opener.document).val(p[1]);
                self.close();
            },'html');
        }
    }

    function confirmkemaskini(){
        var x;
        var r=confirm("Anda pasti untuk kemaskini?");
        if (r==true)
        {
            x="Ya";
        }
        else
        {
            x="Tidak";
        }
    }

    function validation() {
        if($("#nama").val() == ""){
            alert('Sila masukkan " Nama " terlebih dahulu.');
            $("#nama").focus();
            return true;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
            $("#jenis").focus();
            return true;
        }
        return false;
    }

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }
    
    function validate(){
		var proceed = 1;
        if($("#nama").val() == ""){
            alert('Sila pilih Nama terlebih dahulu.');
            $("#nama").focus();
			proceed = 2;
            return false;
        }
        if($("#jenis").val() == ""){
            alert('Sila pilih Jenis Kad Pengenalan terlebih dahulu.');
            $("#jenis").focus();
			proceed = 2;
            return false;
        }
        if($("#nokp1").val() == ""){
            alert('Sila tulis Nombor Pengenalan.');
            $("#alamat1").focus();
			proceed = 2;
            return false;
        }
        if($("#alamat1").val() == ""){
            alert('Sila tulis Alamat.');
            $("#alamat1").focus();
			proceed = 2;
            return false;
        }
        if($("#poskod").val() == ""){
            alert('Sila tulis Poskod.');
            $("#poskod").focus();
			proceed = 2;
            return false;
        }
        if($("#negeri").val() == ""){
            alert('Sila pilih Negeri.');
            $("#negeri").focus();
			proceed = 2;
            return false;
        }
        if($("#aktif").val() == ""){
            alert('Sila pilih Status.');
            $("#aktif").focus();
			proceed = 2;
            return false;
        }
		
		if(proceed == 1){
			$(document).ready(function() {
				$("#simpan").click(function(evt) {
					var idSyarikat=$('#idSyarikat').val(); 
					var idjlb=$('#idjlb').val();     
					var q = $("form").serialize();
					var url = '?' + $(this).attr("name");
			
					$.post(url, q,function(data) {
					//alert(data);
						var x = validate();
						//alert(x);
							self.opener.refreshPagesAddress123(idSyarikat,idjlb);
							self.close();
					});
				});
			});
		}
		//var idSyarikat=document.getElementById('idSyarikat');
		//	alert(idSyarikat.value);
                  
        //            self.opener.refreshingPagingFolder(idSyarikat.value);
        //            self.close();
        return true;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.lelong.PendaftaranJurulelongBerlesenActionBean">
    <div class="subtitle">
        <br>
        <br>
        <fieldset class="aras1">

            <legend>
                Maklumat Syarikat Jurulelong
            </legend>
            <p>
                <label><font color="red">*</font>Nama Syarikat : </label>
                    <s:text id="nama" name="sytJuruLelong.nama" value="${actionBean.sytJuruLelong.nama}"onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
                    <s:hidden id="idSyarikat" name="sytJuruLelong.idSytJlb"/>
                    <s:hidden id="idjlb" value="${actionBean.jurulelong.idJlb}" name="jurulelong.idjlb"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                    <s:text id="jenis" name="sytJuruLelong.jenisPengenalan.nama" readonly="true"/>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                    <s:text id="nokp1" name="sytJuruLelong.noPengenalan" style="width:150px;"/>

                <font color="red" size="1">(cth : 550201045678)</font>
            </p>

            <p>
                <label><font color="red">*</font>Alamat : </label>
                    <s:text id="alamat1" name="sytJuruLelong.alamat1" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat2" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat3" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="sytJuruLelong.alamat4" size="40" maxlength="40" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Poskod : </label>
                <s:text id="poskod" name="sytJuruLelong.poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Negeri : </label>
                    <s:select id="negeri" name="sytJuruLelong.negeri.kod" style="width:150px;">
                        <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Nombor Telefon Pejabat : </label>
                <s:text id="telefon" name="sytJuruLelong.noTelefon1" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
            </p>
            <p>
                <label>Nombor Telefon Bimbit : </label>
                <s:text name="sytJuruLelong.noTelefon2" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);" style="width:150px;"/>&nbsp;
            </p>
            <p>
                        <label><font color="red">*</font>Status :</label>
                        <c:if test="${actionBean.sytJuruLelong.aktif eq 'Y'}">
							
							<s:select name="actionBean.sytJuruLelong.aktif" style="width:200px;">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:option value="Y" selected="selected">Aktif</s:option>
                            <s:option value="T">Tidak Aktif</s:option>
                        </s:select>
                        </c:if>
                        <c:if test="${actionBean.sytJuruLelong.aktif eq 'T'}">
                            
							<s:select name="actionBean.sytJuruLelong.aktif" style="width:200px;">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:option value="Y">Aktif</s:option>
                            <s:option value="T" selected="selected">Tidak Aktif</s:option>
							</s:select>
                        </c:if>
						<c:if test="${actionBean.sytJuruLelong.aktif ne 'T' && actionBean.sytJuruLelong.aktif ne 'Y'}">
                        <s:select id="aktif" name="actionBean.sytJuruLelong.aktif" style="width:200px;">
                            <s:option value="null" selected="selected">-Sila Pilih-</s:option>
                            <s:option value="Y">Aktif</s:option>
                            <s:option value="T">Tidak Aktif</s:option>
                        </s:select>
						</c:if>
                    </p>

            <div class="content" align="center">
                <p>
                    <%--<s:submit name="kemaskiniSyarikat" id="save" onclick="confirmkemaskini()" value="Kemaskini" class="btn"/>--%>
                    <%--<s:submit name="kemaskiniSyarikat" id="save" onclick="return save1(this.form);" value="Kemaskini" class="btn"/>--%>
                    <%--s:button name="kemaskiniSyarikat" id="simpan" onclick="if(validate())doSubmit(${actionBean.idSyarikat},${actionBean.idjlb} this.name)" value="Kemaskini" class="btn"/--%>
                    <s:submit name="kemaskiniSyarikat" id="simpan" onclick="return validate();" value="Kemaskini" class="btn"/>
                    <%--<s:button name="" value="Isi Semula" class="btn" />--%>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <%--                    <s:text id="status" name="status"/> --%>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>