<%-- 
    Document   : tambah_alamat
    Created on : Aug 26, 2010, 12:19:44 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
    function save(event, f){
        if(confirm('Adakah anda pasti?')){

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);

                setTimeout(function(){
                    self.opener.refreshPage();
                    self.close();
                }, 100);
            },'html');
            refreshPage();

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

    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    
    function deleteAlamatTambahan(idPihak, alamat){
        if(confirm("Adakah Anda Pasti?")){
            var url = '${pageContext.request.contextPath}/lelong/pihak_berkepentingan?deleteAlamat&idPihak='+idPihak + '&alamat=' + alamat;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                
                setTimeout(function(){
                    self.opener.refreshPage();
                    self.close();
                }, 100);
            },'html');
            
            refreshPage();
        }
    }
</script>


<s:form beanclass="etanah.view.stripes.lelong.PihakPentingActionBean">

    <div class="subtitle">
        <fieldset class="aras1">            
            <legend>
                Maklumat Alamat 
            </legend>
            <c:if test="${actionBean.mohonPihak.jenis.kod ne 'PG'}">
                <c:if test="${actionBean.negeri eq true}">
                    <legend><br>
                        <font color="red">* Sekiranya alamat di luar persekutuan, sila penuhi ruangan lima </font>
                    </legend>
                </c:if>
            </c:if>
            <div class="subtitle displaytag">
                <s:hidden name="namaPihak" value="${actionBean.mohonPihak.pihak.idPihak}"/>
                <br>
                <label>Nama : ${actionBean.mohonPihak.nama}</label>

                <c:if test="${actionBean.mohonPihak.jenis.kod ne 'PG'}">
                    <p>
                        <label>Alamat Geran</label>
                    </p><br><br> 


                    <p>
                        <label> Alamat :</label>
                        <s:text id="alamat1" name="hakmilikPihakBerkepentingan.alamat1" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="true">
                            ${actionBean.hakmilikPihakBerkepentingan.alamat1}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="hakmilikPihakBerkepentingan.alamat2" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="true">
                            ${actionBean.hakmilikPihakBerkepentingan.alamat2}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="hakmilikPihakBerkepentingan.alamat3" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="true">
                            ${actionBean.hakmilikPihakBerkepentingan.alamat3}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat4" name="hakmilikPihakBerkepentingan.alamat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="true">
                            ${actionBean.hakmilikPihakBerkepentingan.alamat4}
                        </s:text>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text id="poskod" name="hakmilikPihakBerkepentingan.poskod" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;" disabled="true">
                            ${actionBean.hakmilikPihakBerkepentingan.poskod}
                        </s:text>
                    </p>

                    <p>
                        <label>Negeri :</label>
                        <s:select id="negeri" name="hakmilikPihakBerkepentingan.negeri.kod" value="${actionBean.hakmilikPihakBerkepentingan.negeri.kod}" style="width:200px;" disabled="true">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>
                <br><br>
                <c:if test="${actionBean.mohonPihak.alamat.alamat1 ne null}">
                    <p>
                        <label>Alamat Permohonan 1</label>
                    </p><br><br>

                    <p>
                        <label> Alamat :</label>
                        <s:text id="alamat1" name="mohonPihak.alamat.alamat1" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamat.alamat1}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="mohonPihak.alamat.alamat2" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamat.alamat2}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="mohonPihak.alamat.alamat3" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamat.alamat3}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat4" name="mohonPihak.alamat.alamat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamat.alamat4}
                        </s:text>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text id="poskod" name="mohonPihak.alamat.poskod" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamat.poskod}
                        </s:text>
                    </p>

                    <p>
                        <label>Negeri :</label>
                        <s:select id="negeri" name="mohonPihak.alamat.negeri.kod" value="${actionBean.mohonPihak.alamat.negeri.kod}" style="width:200px;" disabled="false">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>
                <br><br>
                <c:if test="${actionBean.mohonPihak.alamatSurat.alamatSurat1 ne null}">
                    <p>
                        <label>Alamat Permohonan 2</label>
                    </p><br><br>

                    <p>
                        <label> Alamat :</label>
                        <s:text id="alamat1" name="mohonPihak.alamatSurat.alamatSurat1" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamatSurat.alamatSurat1}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="mohonPihak.alamatSurat.alamatSurat2" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamatSurat.alamatSurat2}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="mohonPihak.alamatSurat.alamatSurat3" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamatSurat.alamatSurat3}
                        </s:text>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat4" name="mohonPihak.alamatSurat.alamatSurat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamatSurat.alamatSurat4}
                        </s:text>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text id="poskod" name="mohonPihak.alamatSurat.poskodSurat" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;" disabled="false">
                            ${actionBean.mohonPihak.alamatSurat.poskodSurat}
                        </s:text>
                    </p>

                    <p>
                        <label>Negeri :</label>
                        <s:select id="negeri" name="mohonPihak.alamatSurat.negeriSurat.kod" value="${actionBean.mohonPihak.alamatSurat.negeriSurat.kod}" style="width:200px;" disabled="false">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>

                <br><br>
                <%--<c:if test="${actionBean.mohonPihak.jenis.kod ne 'PG'}">--%>
                <p>
                    <%--<label>Alamat  Surat Menyurat 3</label>--%>
                    <c:if test="${actionBean.mohonPihak.alamatSurat.alamatSurat1 eq null}">
                        <label>Alamat  Surat Menyurat 1</label>
                    </c:if>
                    <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                         onmouseover="this.style.cursor='pointer';" onclick="deleteAlamatTambahan('${actionBean.mohonPihak.pihak.idPihak}', 'ketiga');return false;"  title="Klik Untuk Hapus">
                </p><br><br>
                <p>
                    <label> Alamat :</label>
                    <s:text id="alamat1" name="pihakAlamatTamb.alamatKetiga1" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text id="alamat2" name="pihakAlamatTamb.alamatKetiga2" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text id="alamat3" name="pihakAlamatTamb.alamatKetiga3" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text id="alamat4" name="pihakAlamatTamb.alamatKetiga4" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                </p>

                <p>
                    <label>Poskod :</label>
                    <s:text id="poskod" name="pihakAlamatTamb.alamatKetigaPoskod" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                </p>

                <p>
                    <label>Negeri :</label>
                    <s:select id="negeri" name="pihakAlamatTamb.alamatKetigaNegeri.kod" style="width:200px;">
                        <s:option value="null">-Sila Pilih-</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p><br><br>

                <c:if test="${actionBean.mohonPihak.jenis.kod ne 'PG'}">
                    <p>
                        <%--<label>Alamat  Surat Menyurat 4</label>--%>
                        <c:if test="${actionBean.mohonPihak.alamatSurat.alamatSurat1 eq null}">
                            <label>Alamat  Surat Menyurat 2</label>
                        </c:if>
                        <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                             onmouseover="this.style.cursor='pointer';" onclick="deleteAlamatTambahan('${actionBean.mohonPihak.pihak.idPihak}', 'keempat');return false;"  title="Klik Untuk Hapus">
                    </p><br><br>
                    <p>
                        <label> Alamat :</label>
                        <s:text id="alamat1" name="pihakAlamatTamb.alamatKeempat1" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="pihakAlamatTamb.alamatKeempat2" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="pihakAlamatTamb.alamatKeempat3" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat4" name="pihakAlamatTamb.alamatKeempat4" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text id="poskod" name="pihakAlamatTamb.alamatKeempatPoskod" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                    </p>

                    <p>
                        <label>Negeri :</label>
                        <s:select id="negeri" name="pihakAlamatTamb.alamatKeempatNegeri.kod" style="width:200px;">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p><br><br>

                    <p>
                        <%--<label>Alamat  Surat Menyurat 5</label>--%>
                        <c:if test="${actionBean.mohonPihak.alamatSurat.alamatSurat1 eq null}">
                            <label>Alamat  Surat Menyurat 3</label>
                        </c:if>
                        <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                             onmouseover="this.style.cursor='pointer';" onclick="deleteAlamatTambahan('${actionBean.mohonPihak.pihak.idPihak}','kelima');return false;"  title="Klik Untuk Hapus">
                    </p><br><br>
                    <p>
                        <label> Alamat :</label>
                        <s:text id="alamat1" name="pihakAlamatTamb.alamatKelima1" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat2" name="pihakAlamatTamb.alamatKelima2" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat3" name="pihakAlamatTamb.alamatKelima3" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label> &nbsp;</label>
                        <s:text id="alamat4" name="pihakAlamatTamb.alamatKelima4" onblur="this.value=this.value.toUpperCase();" style="width:200px;"/>
                    </p>

                    <p>
                        <label>Poskod :</label>
                        <s:text id="poskod" name="pihakAlamatTamb.alamatKelimaPoskod" size="19" maxlength="5" onchange="validatePoskodLength(this.value);" onkeyup="validateNumber(this,this.value);" style="width:200px;"/>
                    </p>

                    <p>
                        <label>Negeri :</label>
                        <s:select id="negeri" name="pihakAlamatTamb.alamatKelimaNegeri.kod" style="width:200px;">
                            <s:option value="null">-Sila Pilih-</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </c:if>
                </p><br><br>

                <p align="center"><label></label>
                    <br>
                    <s:button name="simpanAlamat" value="Simpan" class="btn" onclick="save(this.name, this.form);" />
                    <%--<s:button name="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);" />
                    <%--<s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>--%>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>


            <br>
        </fieldset>
    </div>
</s:form>