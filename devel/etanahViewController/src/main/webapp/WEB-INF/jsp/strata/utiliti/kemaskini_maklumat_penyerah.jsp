<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE html>

<script type="text/javascript">
    function validation3(){
     //  alert("masukk!!");
        if($("#penyerahNama").val() == ""){
            alert('Sila masukan " Nama " terlebih dahulu.');
            $("#penyerahNama").focus();
            return false;
        } 
        else if($("#penyerahAlamat1").val() == ""){
            alert('Sila masukan " Alamat " terlebih dahulu.');
            $("#penyerahNama").focus();
            return false;
        } 
        else if($("#penyerahAlamat4").val() == ""){
            alert('Sila masukan " Bandar " terlebih dahulu.');
            $("#penyerahNama").focus();
            return false;
        } 
        else if($("#penyerahPoskod").val() == ""){
            alert('Sila masukan " Poskod " terlebih dahulu.');
            $("#penyerahNama").focus();
            return false;
        } 
        else if($("#penyerahNegeri").val() == ""){
            alert('Sila pilih " Negeri " terlebih dahulu.');
            $("#penyerahNama").focus();
            return false;
        } 
        else{
            return true;
        }
    }
    
     function doSubmit(e,f,g) {
        var q = $(f).formSerialize();
        f.action = f.action + '?' + e + '&idPermohonan=' + g + '&popup&' + q;
        f.submit();
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">
    <%--<s:errors/>--%>
    <%--<s:messages/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>

            <!--<p>-->
            <!--<label>Carian Penyerah :</label>-->
            <%--<c:if test="${empty SSHMA}">--%>       
            <%--<c:if test="${actionBean.kodNeg eq 'melaka'}">--%>
            <%--<s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">--%>
            <%--<s:option value="0">INDIVIDU / SYARIKAT</s:option>--%>
            <%--<s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>--%>
            <%--</s:select>--%>
            <%--</c:if>--%>
            <%--<c:if test="${actionBean.kodNeg eq 'n9'}">--%>    
            <%--<s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">--%>
            <%--<s:option value="0">INDIVIDU / SYARIKAT</s:option>--%>
            <%--<s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>--%>
            <%--</s:select>--%>
            <%--</c:if>--%> 
            <%--</c:if>--%>
            <%--<c:if test="${!empty SSHMA}">--%>
            <%--<s:select name="penyerahKod.kod" id="penyerahKod" onchange="javascript:updateKodPenyerahInputs();">--%>
            <%--<s:option value="06">Jabatan Kerajaan</s:option>--%>
            <%--<s:option value="07">Badan Berkanun</s:option>--%>
            <%--<s:option value="00">Unit Dalaman</s:option>--%>
            <%--</s:select>--%>
            <%--</c:if>--%>
            <%--<s:text name="idPenyerah" size="5" id="idPenyerah" maxlength="5" />--%>
            <!--<input type="button" id="carianPenyerah" value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />-->
            <!--(Biarkan kosong dan klik "Cari" untuk membuat rujukan)-->
            <!--</p>-->
            <!--                    <p>
                                    <label for="Jenis Pengenalan">Carian No. Pengenalan :</label>
            <%--<s:select name="penyerahJenisPengenalan.kod" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">--%>
            <%--<s:option value="0">Pilih Jenis...</s:option>--%>
            <%--<s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>--%>
            <%--</s:select>--%>
            <em>*</em>
            <%--<s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);" onblur="doUpperCase(this.id)"/>--%> 
            <font title="No KP Baru: 780901057893, No KP Lama: A2977884, No Syarikat: 127776-V, No Pertubuhan: 432483-U"><em>[cth: 780901057893]</em></font>
            <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                   onclick="javascript:populatePenyerah(this);" />
            </p>-->

            <p>
                <label>Jenis Penyerah :</label>
                <%--<s:text name="penyerahKodNama" id="penyerahKodNama" size="42" onblur="doUpperCase(this.id)" readonly="true"/>--%>
                <s:select name="penyerahKod" id="penyerahKod">
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod" />
                </s:select>
                <!--<input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak" onclick="javascript:populatePenyerah(this);"/>-->
            </p>
            <p>
                <label><em>*</em>Nama :</label>
                <s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
            </p>
            <p>
                <label><em>*</em>Alamat :</label>
                <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="42" onblur="doUpperCase(this.id)" class="required"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="42" onblur="doUpperCase(this.id)"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="42" onblur="doUpperCase(this.id)"/>
            </p>
            <p>
                <label><em>*</em>Bandar</label>
                <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="42" onblur="doUpperCase(this.id)"/>
            </p>
            <p>
                <label><em>*</em>Poskod</label>
                <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
                <em>5 Digit [cth : 12000]</em>
            </p>
            <p>
                <label><em>*</em>Negeri</label>
                <s:select name="penyerahNegeri" id="penyerahNegeri" class="required">
                    <s:option value="">Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select> 
                <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
            </p>
            <p>
                <label>No.Telefon</label>
                <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
            </p>
            <p>
                <label>Email</label>
                <s:text name="penyerahEmail" id="penyerahEmail" size="42"/>
            </p>
            <p></p>

            <p>
                <label>&nbsp;</label>
                <%--s:submit name="updateMaklumatPenyerah" value="Kemaskini" class="btn" onclick="if(validation()==true){doSubmit('simpanPenyerah',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp; --%>
                 <s:button class="btn" value="Kemaskini" name="updateMaklumatPenyerah" onclick="if(validation3()==true){doSubmit('updateMaklumatPenyerah',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp;
                
                <s:hidden name="idPermohonan" id="idPermohonan"/>
                <%--<s:button class="btn" value="Isi Semula" name="resetPenyerah" onclick="clearForm()"/>--%>
                <s:button class="btn" value="Isi Semula" name="resetPenyerah" onclick="if (confirm('Pasti untuk memadam rekod?')){doSubmit('resetPenyerah',this.form,'${actionBean.permohonan.idPermohonan}')};"/>&nbsp;
                </p>

        </fieldset>
    </div>

</s:form>
