<%-- 
    Document   : tambah_jabatan_teknikal
    Created on : Feb 2, 2010, 1:48:16 PM
    Author     : muhammad.amin
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

<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil"/>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Jabatan Teknikal
            </legend>
            <br/>
            <p>
                <label>Jabatan :</label>
                <s:select name="pihak.suratNegeri.kod" style="width:450px">
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodAgensi}" label="nama" value="kod" />
                </s:select>
            </p>
            <%--            <p>
                            <label>&nbsp;</label>
                            <s:button name="" id="" value="Cari" class="btn"/>
                        </p>
                        <br>--%>

            <%--  <p>
                  <label for="nama">Nama :</label>
                  <s:text name="pihak.nama" id="nama" size="40"/>
              </p>
              <p>
                  <label for="alamat">Alamat Berdaftar :</label>
                  <s:text name="pihak.alamat1" size="40"/>
              </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat2" size="40"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.alamat4" size="40"/>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.poskod" size="40" maxlength="5"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihak.negeri.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
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
            --%><br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="" value="Simpan" class="btn" onclick="self.close()"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </div>
</s:form>
