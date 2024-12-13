<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript">
    function save(event, f){
      

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/permohonanPNSS?copyPenyerah';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function popup(event, f){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/permohonanPNSS?cariBadanUrus';
        window.open(url, 'eTanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
        
    }
    <%--    $(document).ready(function() {
            var url = '${pageContext.request.contextPath}/strata/permohonanPNSS?reload';
            $.ajax({
                type:"GET",
                url : url,
                success : function(data) {
                    $('#folder_div',opener.document).html(data);
                }
            });
        });--%>
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.strata.PenamatanStrataActionBean">
    <s:messages/>
    <s:errors/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama : </label>
                ${actionBean.permohonan.penyerahNama} &nbsp;
            </p>
            <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod eq 'B' or actionBean.permohonan.penyerahJenisPengenalan.kod eq 'b'}">
                <p>
                    <label>Jenis Penyerah :</label>
                    Individu/Syarikat&nbsp;
                </p>

                <p>
                    <label>No.Pengenalan :</label>
                    ${actionBean.permohonan.penyerahJenisPengenalan.nama}&nbsp;
                </p>

                <p>
                    <label>No.Rujukan Penyerah :</label>
                    ${actionBean.permohonan.idPenyerah}&nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.permohonan.penyerahJenisPengenalan.kod ne 'B' and actionBean.permohonan.penyerahJenisPengenalan.kod ne 'b'}">
                <p>
                    <label>Jenis Penyerah :</label>
                    ${actionBean.permohonan.kodPenyerah.nama}&nbsp;
                </p>

                <p>
                    <label>No.Rujukan Penyerah :</label>
                    ${actionBean.permohonan.idPenyerah}&nbsp;
                </p>
            </c:if>
            <p>
                <label>Alamat :</label>
                <font style="text-transform: uppercase">${actionBean.permohonan.penyerahAlamat1} &nbsp;</font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label>&nbsp; </label>
                    <font style="text-transform: uppercase">${actionBean.permohonan.penyerahAlamat2} &nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label>&nbsp; </label>
                    <font style="text-transform: uppercase">${actionBean.permohonan.penyerahAlamat3} &nbsp;</font>
                </p>
            </c:if>
            <%--<c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">--%>
            <p>
                <label>Bandar :</label>
                <font style="text-transform: uppercase">${actionBean.permohonan.penyerahAlamat4} &nbsp;</font>
            </p>
            <%--</c:if>--%>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod} &nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform: uppercase">${actionBean.permohonan.penyerahNegeri.nama} &nbsp;</font>
            </p>
            <%--  <p><label>&nbsp;</label>
                  <s:checkbox name="pemohon_penyerah" onclick="save(this.name, this.form);" ></s:checkbox>
                  Penyerah adalah Badan Pengurusan--%>
            <p>
                <label>&nbsp;</label>
            </p>
        </fieldset>
    </div>
    <%--
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Badan Pengurusan</legend>

            <p>
                <label>Nama : </label>
                <c:if test="${actionBean.pemohon ne null}">
                    ${actionBean.pemohon.pihak.nama}
                </c:if>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>
                <s:button name="cariBadanUrus" value="Cari" class="btn" onclick="popup(this.name, this.form);"/>
                <s:submit name="cariBadanUrus" value="Simpan" class="btn"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>
                <c:if test="${actionBean.pemohon eq null}"></c:if>
                ${actionBean.pemohon.pihak.jenisPengenalan.nama}

            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.noPengenalan}

            </p>
            <p>
                <label>Alamat :</label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.alamat1}
            </p>
            <p>
                <label>&nbsp; </label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.alamat2}
            </p>
            <p>
                <label>&nbsp; </label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.alamat3}
            </p>
            <p>
                <label>&nbsp; </label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.alamat4}
            </p>
            <p>
                <label>Poskod :</label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.poskod}

            </p>
            <p>
                <label>Negeri :</label>
                <c:if test="${actionBean.pemohon eq null}">
                    <label>&nbsp;</label>
                </c:if>${actionBean.pemohon.pihak.negeri.nama} </p>
            <br>
            <br>
        </fieldset>
    </div>--%>
    <p align="center">
        <%--<s:submit name="Simpan" value="Simpan" class="btn"/>--%>
        <%--<a href="skim_strata?showForm1"<s:submit name="isisemula" value="Isi Semula" class="btn"/></a>--%>
    </p>


</s:form>