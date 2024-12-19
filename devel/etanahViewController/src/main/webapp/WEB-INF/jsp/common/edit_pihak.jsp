<%-- 
    Document   : edit_pihak
    Created on : Jan 12, 2010, 11:50:06 AM
    Author     : khairil
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

</script>

<s:form beanclass="etanah.view.stripes.PihakBerkepentinganActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>

            <p>
                <label>Nama :</label>
                <s:text name="pihak.nama" size="40"/>&nbsp;<s:hidden name="pihak.idPihak"/>
                <s:hidden name="idHakmilik" value='${idHakmilik}'/>
                <s:hidden name="idPBK" value='${idPBK}'/>
            </p>
            <p>
                <label>Kod Pengenalan :</label>
                <s:select name="kodPengenalan" id="kodPengenalan" value="${pihak.jenisPengenalan.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>  
            <p>
                <label>Nombor Pengenalan :</label>
                <s:text name="pihak.noPengenalan" size="40"/>&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan Lama:</label>
                <s:text name="pihak.noPengenalanLain" size="40"/>&nbsp;
            </p>
            <p>
                <label>Bangsa :</label>
                <s:select name="pihak.bangsa.kod" id="bangsa">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Warganegara :</label>
                <s:select name="pihak.wargaNegara.kod" id="warganegara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" />
                </s:select>
            </p>
            
            <p> 
                <label>Jenis Pihak Berkepentingan :</label>
                    <s:select class="wideselect" id="jenisPihak" name="pihakBerkepentingan.jenis.kod" value="">
                      <%--<s:option value="" >Sila Pilih</s:option>--%>
                      <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                    </s:select>
            </p>
            
            <p>
                <label>Syarikat Tubuh :</label>
                <s:select class="wideselect" name="pihak.penubuhanSyarikat.kod" id="sykt">
                    <s:option value="">Sila Pilih</s:option>
                   <s:options-collection collection="${listUtil.senaraiKodPenubuhanSyarikat}" label="nama" value="kod" />
                </s:select>
                    
            </p>
            <p>
                <label for="alamat">Alamat Berdaftar :</label>
                <s:text name="pihak.alamat1" size="40" style="text-transform:uppercase;"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" size="40" style="text-transform:uppercase;"/>
            </p>


            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" size="40" style="text-transform:uppercase;"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="pihak.alamat4" size="40" style="text-transform:uppercase;"/>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.poskod" size="40" maxlength="5"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihak.negeri.kod" id="negeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Alamat Surat Menyurat :</label>
                <s:text name="pihak.suratAlamat1" size="40" style="text-transform:uppercase;"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40" style="text-transform:uppercase;"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40" style="text-transform:uppercase;"/>
            </p>
            <p>
                <label>Bandar :</label>
                <s:text name="pihak.suratAlamat4" size="40" style="text-transform:uppercase;"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPihak" id="simpan 1" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <%--<c:if test="${actionBean.p.kodUrusan.kod ne 'BETUL'}">--%>
                    <%--<s:button name="simpanPihakBerkelompok" id="simpan" value="Simpan Berkelompok" class="btn" onclick="save(this.name, this.form);"/>--%>                                     
                <%--</c:if>--%>               
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>