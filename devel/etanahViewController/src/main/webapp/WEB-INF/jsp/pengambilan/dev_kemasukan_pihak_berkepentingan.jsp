<%--
    Document   : dev_kemasukan_pihak_berkepentingan
    Created on : Feb 11, 2010, 10:11:37 AM
    Author     : abu.mansur
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
    });
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            if(data == '1')
            {
                alert('Sila masukan data pada label yang bertanda *. ');
            }else{$('#page_div',opener.document).html(data);

                self.close();}

        },'html');
    }

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

        function copyAddCaw(){
        if($('input[name=addCaw]').is(':checked')){
            $('#suratAlamatCaw1').val($('#alamatCaw1').val());
            $('#suratAlamatCaw2').val($('#alamatCaw2').val());
            $('#suratAlamatCaw3').val($('#alamatCaw3').val());
            $('#suratAlamatCaw4').val($('#alamatCaw4').val());
            $('#suratPoskodCaw').val($('#poskodCaw').val());
            $('#suratNegeriCaw').val($('#negeriCaw').val());
        }else{
            $('#suratAlamatCaw1').val('');
            $('#suratAlamatCaw2').val('');
            $('#suratAlamatCaw3').val('');
            $('#suratAlamatCaw4').val('');
            $('#suratPoskodCaw').val('');
            $('#suratNegeriCaw').val('');

        }
    }

    function copyAdd(){
        if($('input[name=add1]').is(':checked')){
            $('#suratAlamat1').val($('#alamat1').val());
            $('#suratAlamat2').val($('#alamat2').val());
            $('#suratAlamat3').val($('#alamat3').val());
            $('#suratAlamat4').val($('#alamat4').val());
            $('#suratPoskod').val($('#poskod').val());
            $('#suratNegeri').val($('#negeri').val());
        }else{
            $('#suratAlamat1').val('');
            $('#suratAlamat2').val('');
            $('#suratAlamat3').val('');
            $('#suratAlamat4').val('');
            $('#suratPoskod').val('');
            $('#suratNegeri').val('');

        }
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.pbtActionBean" >

        <s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.suku.kod"/>
        <s:hidden name="pihak.alamat1"/>
        <s:hidden name="pihak.alamat2"/>
        <s:hidden name="pihak.alamat3"/>
        <s:hidden name="pihak.alamat4"/>
        <s:hidden name="pihak.poskod"/>
        <s:hidden name="pihak.negeri.kod"/>
        <s:hidden name="pihak.negeri.nama"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihak.suratAlamat1"/>
        <s:hidden name="pihak.suratAlamat2"/>
        <s:hidden name="pihak.suratAlamat3"/>
        <s:hidden name="pihak.suratAlamat4"/>
        <s:hidden name="pihak.suratNegeri.kod"/>
        <s:hidden name="pihak.suratNegeri.nama"/>
        <s:hidden name="pihak.suratPoskod"/>
        <s:hidden name="urusan" value="${urusan}"/>

        <c:if test="${tuanTanah}">
            <s:text name="tuanTanah" value="${tuanTanah}"/>
        </c:if>
        <s:errors/>
        <s:messages/>
        <c:if test="${!actionBean.tambahCawFlag}">

            <fieldset class="aras1">
                <c:if test="${pemohon}">
                    <legend>Kemasukan Maklumat Pemohon</legend>
                </c:if>

                <c:if test="${!pemohon}">
                    <legend>Kemasukan Maklumat Penerima</legend>
                </c:if>

                <%--<legend>Kemasukan Pihak Berkepentingan</legend>--%>

                <c:if test="${!actionBean.cariFlag}">

                    <p>
                        <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                        <s:select name="pihak.jenisPengenalan.kod">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label for="No Pengenalan"><font color="red">*</font>No Pengenalan :</label>
                        <s:text name="pihak.noPengenalan" id="kp" size="40"/>
                    </p>
                  <%--  <p>
                        <label for="Nama"><font color="red">*</font>No Pengenalan :</label>
                        <s:text name="pihak.nama" id="nama" size="40"/>
                    </p>--%>
                </c:if>

                <c:if test="${actionBean.cariFlag}">

                    <c:if test="${!actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama">Nama :</label>
                            ${actionBean.pihak.nama}&nbsp;
                        </p>
                        <p>
                            <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                            ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                        </p>
                        <p>
                            <label for="No Pengenalan">No Pengenalan :</label>
                            ${actionBean.pihak.noPengenalan}&nbsp;
                        </p>

                        <c:if test="${actionBean.pihak.bangsa.kod ne null}">
                            <p>
                                <label>Bangsa :</label>
                                ${actionBean.pihak.bangsa.nama}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pihak.suku.kod ne null}">
                            <p>
                                <label>Jenis Suku :</label>
                                ${actionBean.pihak.suku.nama}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="permohonanPihak.jenis.kod" style="width:400px">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat">Alamat Berdaftar :</label>
                            ${actionBean.pihak.alamat1}&nbsp;
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat2}&nbsp;
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat3}&nbsp;
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            ${actionBean.pihak.alamat4}&nbsp;
                        <p>
                            <label>Poskod :</label>
                            ${actionBean.pihak.poskod}&nbsp;
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            ${actionBean.pihak.negeri.nama}&nbsp;
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat:</label>
                            <s:text name="pihak.suratAlamat1" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" size="40"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" size="40" maxlength="5"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>No.Telefon :</label>
                            <s:text name="pihak.noTelefon1" maxlength="11" id="notelefon"  /><font color="red">*</font>

                        </p>
                        <p>
                            <label>No.Fax :</label>
                            <s:text name="pihak.noTelefon2" maxlength="11" id="notelefon"  /><font color="red">*</font>

                        </p>
                        <p>
                            <label>Email :</label>
                            <s:text name="pihak.email" size="40" maxlength="100" />

                        </p>
                    </c:if>
                    <%--tiada data--%>
                    <c:if test="${actionBean.tiadaDataFlag}">
                        <p>
                            <label for="nama"><font color="red">*</font>Nama :</label>
                            <s:text name="pihak.nama" id="nama" size="40"/>
                        </p>

                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>No Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40"/>
                        </p>

                        <p>
                            <label>Bangsa :</label>
                            <s:select name="pihak.bangsa.kod">
                                <s:option>Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label for="Suku">Jenis Suku :</label>
                            <s:select name="pihak.suku.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodSuku}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <c:if test="${!pemohon}">
                            <p>
                                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                                <s:select name="permohonanPihak.jenis.kod">
                                    <s:option value="" >Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                                </s:select>
                            </p>
                        </c:if>
                        <p>
                            <label for="alamat">Alamat Berdaftar :</label>
                            <s:text name="pihak.alamat1" size="40" id="alamat1"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat2" size="40" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat3" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.alamat4" size="40" id="alamat4"/>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.poskod" size="40" maxlength="5" id="poskod"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/>
                            <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                        </p>
                        <p>
                            <label for="alamat">Alamat Surat-Menyurat:</label>
                            <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="40"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="40"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>No.Telefon :</label>
                            <s:text name="pihak.noTelefon1" maxlength="10" id="notelefon"  /><font color="red">*</font>

                        </p>
                        <p>
                            <label>No.Fax :</label>
                            <s:text name="pihak.noTelefon2" maxlength="10" id="notelefon"  /><font color="red">*</font>

                        </p>
                        <p>
                            <label>Email :</label>
                            <s:text name="pihak.email" size="40" maxlength="40" />

                        </p>

                    </c:if>
                    <div class="content" align="center">
                        <br>
                        Maklumat Cawangan

                        <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                            <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                            <display:column property="namaCawangan" title="Nama"/>
                            <display:column title="Alamat" >${line.suratAlamat1}
                                <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                                ${line.suratAlamat2}
                                <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                                ${line.suratAlamat3}
                                <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                                ${line.suratAlamat4}</display:column>
                            <display:column property="suratPoskod" title="Poskod" />
                            <display:column property="suratNegeri.nama" title="Negeri" />

                        </display:table>
                    </div>
                </c:if>

                <c:if test="${!actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${pemohon}">
                            <s:submit name="cariPihakPemohon" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!pemohon}">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                        </c:if>

                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.cariFlag}">
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${pemohon}">
                            <s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <s:submit name="tambahCawanganPemohon" value="Tambah Cawangan" class="btn"/>
                        </c:if>

                        <c:if test="${!pemohon}">
                            <s:button name="simpanPihakKepentinganPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                            <s:submit name="tambahCawangan" value="Tambah Cawangan" class="btn"/>
                        </c:if>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </c:if>

        <%--add new cawangan--%>

        <c:if test="${actionBean.tambahCawFlag}">
            <s:hidden name="pihakCawangan.negeri.kod"/>

            <fieldset class="aras1">

                <c:if test="${pemohon}">
                    <legend>Kemasukan Cawangan Pemohon</legend>
                </c:if>

                <c:if test="${!pemohon}">
                    <legend>Kemasukan Cawangan Penerima</legend>
                </c:if>
                <p>
                    <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="nama" size="40"/>
                </p>
                <p>
                    <label for="alamat">Alamat Berdaftar :</label>
                    <s:text name="pihakCawangan.alamat1" id="alamatCaw1" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat2" id="alamatCaw2" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat3" id="alamatCaw3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.alamat4" id="alamatCaw4" size="40"/>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.poskod" size="40" id="poskodCaw" maxlength="5"/>
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.negeri.kod" id="negeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="checkbox" id="addCaw" name="addCaw" value="" onclick="copyAddCaw();"/>
                    <font color="red">Alamat surat-menyurat sama dengan alamat berdaftar.</font>
                </p>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat:</label>
                    <s:text name="pihakCawangan.suratAlamat1" id="suratAlamatCaw1" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat2" id="suratAlamatCaw2" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat3" id="suratAlamatCaw3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pihakCawangan.suratAlamat4" id="suratAlamatCaw4" size="40"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pihakCawangan.suratPoskod" id="suratPoskodCaw" size="40" maxlength="5"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="pihakCawangan.suratNegeri.kod" id="suratNegeriCaw">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>

                <p>
                    <label for="alamat">Nombor Telefon:</label>
                    <s:text name="pihakCawangan.noTelefon1" size="40"/>
                </p>

                <p>
                    <label>&nbsp;</label>

                    <c:if test="${pemohon}">
                        <s:submit name="simpanCawanganPemohon" value="Simpan" class="btn"/>
                    </c:if>

                    <c:if test="${!pemohon}">
                        <s:submit name="simpanCawangan" value="Simpan" class="btn"/>
                    </c:if>

                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </fieldset>
        </c:if>
    </s:form>
</div>