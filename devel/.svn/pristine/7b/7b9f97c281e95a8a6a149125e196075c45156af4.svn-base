<%-- 
    Document   : cetak_semula_resit
    Created on : Mar 24, 2010, 4:32:56 PM
    Author     : abdul.hakim
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <title>Kaunter: Cetak Semula</title>

    </head>
    <body>
        <script language="javascript" >
            $(document).ready(function() {
                $("#resit").val("");
                $("#resit1").val("");
                $("#daftar").attr("disabled", "true");
            });
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript">
            function validate(x){
                if(x == "cetak_semula_resit"){
                    var resit = document.getElementById('resit');
                    var idMohon = document.getElementById('mohon');
                    var accnt = document.getElementById('acc');
                    var idHm = document.getElementById('hm');
                    if((resit.value == "")&&(idMohon.value == "")&&(accnt.value == "")&&(idHm.value == "")){
                        alert("Sila Masukkan Nombor Resit, ID Permohonan, Nombor Akaun atau ID Hakmilik untuk membuat carian.");
                        $("#resit").focus();
                        return false;
                    }
                }
                if(x == "cetak_semula"){
                    var resit = document.getElementById('resit1');
                    if(resit.value == ""){
                        alert("Sila Masukkan Nombor Resit.");
                        $("#resit1").focus();
                        return false;
                    }
                }
                if(x == "cetak_pelan"){
                    var resit = document.getElementById('resit2');
                    var idMohon = document.getElementById('nama');
                    if((resit.value == "")&&(idMohon.value == "")){
                        alert("Sila Masukkan Nombor Resit Atau Nama Pembeli.");
                        $("#resit2").focus();
                        return false;
                    }
                }
                if(x == "cetak_surat_akuan"){
                    var resit = document.getElementById('idMohon');
                    if(resit.value == ""){
                        alert("Sila Masukkan ID Permohonan.");
                        $("#idMohon").focus();
                        return false;
                    }
                }
                return true;
            }
            function button(){
                $("#daftar").removeAttr("disabled");
            }
        </script>
        <stripes:messages />
        <stripes:errors />

        <!--  Cetak Semula Resit -->
        <stripes:form beanclass="etanah.view.stripes.hasil.CetakSemulaResitActionBean" id="cetak_semula_resit">
            <div class="subtitle">
                <div align="center">
                    <table style="width:99.2%"  bgcolor="green">
                        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">UTILITI: Cetak Semula</font>
                                </div></td></tr>
                    </table>
                </div>
                <fieldset class="aras1">
                    <legend>Cetak Resit Cukai Tanah Semula</legend>
                    <p>
                        <label>Nombor Resit:</label>
                        <stripes:text name="dokumenKewangan.idDokumenKewangan" size="30" id="resit" maxlength="16" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <%--<p>
                        <label>ID Permohonan :</label>
                        <stripes:text name="permohonan.idPermohonan" size="30" maxlength="20" id="mohon" onblur="this.value=this.value.toUpperCase();"/>
                    </p>--%>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <p>
                            <label>Nombor Akaun :</label>
                            <stripes:text name="akaun.noAkaun" size="30" id="acc" maxlength="28" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                    </c:if>
                    <p>
                        <label>ID Hakmilik :</label>
                        <stripes:text name="hakmilik.idHakmilik" id="hm" size="30" maxlength="28" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="search" value="Cari" class="btn" onclick="return validate('cetak_semula_resit');"/>
                        <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cetak_semula_resit');"/>
                    </p>
                </fieldset>
            </div>
            <c:if test="${actionBean.flag}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Hasil Carian</legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiDokumenKewangan}" id="line">
                                <display:column title="Pilih"><div align="center"><stripes:radio name="radio" value="${line.idDokumenKewangan}" onclick="button()" id="rBtn"/></div></display:column>
                                <display:column title="Nombor Resit" property="idDokumenKewangan"/>
                                <display:column title="Dibayar Oleh" property="isuKepada"/>
                                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                                <display:column property="amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                            </display:table>
                            <stripes:submit name="next" value="Terus" class="btn" id="daftar"/>
                        </div>
                    </fieldset>
                </div>
            </c:if>
        </stripes:form>
        <p></p>
        <!--  Belakang Cek/Kiriman Wang/Wang Pos-->
        <stripes:form beanclass="etanah.view.stripes.hasil.CetakSemulaActionBean" id="cetak_semula">

            <div class="subtitle">

                <fieldset class="aras1">
                    <legend>Cetak Belakang Cek/Kiriman Wang/Wang Pos Semula</legend>

                    <p>
                        <label>Nombor Resit :</label>
                        <stripes:text name="dokumenKewangan.idDokumenKewangan" id="resit1" size="30" maxlength="16" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="search" value="Cari" class="btn" onclick="return validate('cetak_semula');"/>
                        <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cetak_semula');"/>
                    </p>
                </fieldset>
            </div>
        </stripes:form>
        <p></p>
        <!-- Cetak Pelan comment utk FAT melaka-->
        <%--<stripes:form beanclass="etanah.view.stripes.hasil.CetakPelanActionBean" id="cetak_pelan">
            <div class="subtitle">

                <fieldset class="aras1">
                    <legend>Cetak Pelan Semula</legend>

                    <p>
                        <label>Nombor Resit :</label>
                        <stripes:text name="dokumenKewangan.idDokumenKewangan" size="30" id="resit2" maxlength="16" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Nama Pembeli :</label>
                        <stripes:text name="pembeli" size="30" maxlength="20" id="nama" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="searchPelan" value="Cari" class="btn" onclick="return validate('cetak_pelan');"/>
                        <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cetak_pelan');"/>
                    </p>
                </fieldset>
            </div>
            <c:if test="${actionBean.flag1}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Hasil Carian</legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiDokumenKewangan}" id="line">
                                <display:column title="Pilih"><div align="center"><stripes:radio name="radio" value="${line.idDokumenKewangan}" onclick="button()" id="rBtn"/></div></display:column>
                                <display:column title="Nombor Resit" property="idDokumenKewangan"/>
                                <display:column title="Pembeli" property="isuKepada"/>
                                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                                <display:column property="amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                            </display:table>
                            <stripes:submit name="next" value="Terus" class="btn" id="daftar"/>
                        </div>
                    </fieldset>
                </div>
            </c:if>
        </stripes:form>--%>
        <p></p>
        <!--  Cetak Semula Surat Akuan-->
        <%--<stripes:form beanclass="etanah.view.stripes.hasil.CetakSemulaSuratAkuanActionBean" id="cetak_surat_akuan">

            <div class="subtitle">

                <fieldset class="aras1">
                    <legend>Cetak Semula Surat Akuan</legend>

                    <p>
                        <label>ID Permohonan :</label>
                        <stripes:text name="permohonan.idPermohonan" id="idMohon" size="30" maxlength="18" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <stripes:submit name="search" value="Cari" class="btn" onclick="return validate('cetak_surat_akuan');"/>
                        <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cetak_surat_akuan');"/>
                    </p>
                </fieldset>
            </div>
        </stripes:form>--%>
        <div align="center">
            <table style="width:99.2%"  bgcolor="green">
                <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">&nbsp;</font>
                </div></td></tr>
            </table>
        </div>
</body>
</html>

