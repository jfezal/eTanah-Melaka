<%-- 
    Document   : maklumat_pihak
    Created on : Mar 12, 2013, 12:19:27 PM
    Author     : Mohammad Hafifi
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready(function(){        
        $("#copyAddress").live("change", function(){
            if($(this).is(":checked") == true){
                copyAddress();
            }else{
               
            }
        });
    });
    function validateForm(){
        if($("#namaPemohon").val() == ""){
            alert("Sila isikan Nama");
            return false;
        }
        
        return true;
    }
    function copyAddress(){
        $("#alamatSurat1").val($("#alamatTetap1").val());
        $("#alamatSurat2").val($("#alamatTetap2").val());
        $("#alamatSurat3").val($("#alamatTetap3").val());
        $("#alamatSuratPoskod").val($("#alamatTetapPoskod").val());
        $("#alamatSuratNegeri").val($("#alamatTetapNegeri").val());
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPihakActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}" id="idPermohonan"/>
    <s:hidden name="kodCawangan" value="${actionBean.kodCawangan}" id="kodCawangan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak
            </legend>
            <c:if test="${edit}">
                <div class="content" >
                    <p>
                        <label><em>*</em> Nama :</label>
                        <s:text name="namaPemohon" id="namaPemohon" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.namaPemohon}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>Alamat Tetap :</label>
                        <s:text name="alamatTetap1" id="alamatTetap1" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatTetap1}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamatTetap2" id="alamatTetap2" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatTetap2}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamatTetap3" id="alamatTetap3" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatTetap3}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="alamatTetapPoskod" id="alamatTetapPoskod" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatTetapPoskod}"/>&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="alamatTetapNegeri" id="alamatTetapNegeri" value="${actionBean.alamatTetapNegeri}">
                            <s:option>Pilih ...</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        &nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <input type="checkbox" id="copyAddress"/> Alamat Surat Menyurat sama seperti di atas
                    </p>
                    <p>
                        <label>Alamat Surat Menyurat :</label>
                        <s:text name="alamatSurat1" id="alamatSurat1" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatSurat1}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamatSurat2" id="alamatSurat2" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatSurat2}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:text name="alamatSurat3" id="alamatSurat3" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatSurat1}" style="width:250px"/>&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="alamatSuratPoskod" id="alamatSuratPoskod" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.alamatSuratPoskod}"/>&nbsp;
                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="alamatSuratNegeri" id="alamatSuratNegeri" value="${actionBean.alamatSuratNegeri}">
                            <s:option>Pilih ...</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </div>
                <p align="right">
                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                </p>
            </c:if>
            <c:if test="${view}">
                <div class="content" >
                    <p>
                        <label>Nama :</label>
                        ${actionBean.pemohon.nama}&nbsp;
                    </p>
                    <p>
                        <label>Alamat Tetap :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamat.alamat1 eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamat.alamat1}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.pemohon.alamat.alamat2}&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.pemohon.alamat.alamat3}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamat.poskod eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamat.poskod}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label>Negeri :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamat.negeri.kod eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamat.negeri.nama}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        &nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                    </p>
                    <br>
                    <p>
                        <label>Alamat Surat Menyurat :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamatSurat.alamatSurat1 eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamatSurat.alamatSurat1}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.pemohon.alamatSurat.alamatSurat2}&nbsp;
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.pemohon.alamatSurat.alamatSurat3}&nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamatSurat.poskodSurat eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamatSurat.poskodSurat}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>
                        <label>Negeri :</label>
                        <c:choose>
                            <c:when test="${actionBean.pemohon.alamatSurat.negeriSurat.kod eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                ${actionBean.pemohon.alamatSurat.negeriSurat.nama}&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </c:if>

        </fieldset>

    </div>
</s:form>